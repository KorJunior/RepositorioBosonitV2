package org.example;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AppConSpark {
    static File archivoEstructura = new File("estructura.txt");
    static File archivoDatos = new File("vehiculos.txt");

    public static void main(String[] args) {
        Estructura estructura = new Estructura(archivoEstructura);
        SparkSession spark = SparkSession.builder()
                .appName("Lector Fichero Cobol")
                .master("local")
                .getOrCreate();
        JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());

        String contenidoArchivo = jsc.textFile(archivoDatos.getPath()).first();

        int longitudRegistro = estructura.obtenerTamañodeBulto();

        List<String> registros = new ArrayList<>();
        for (int start = 0; start < contenidoArchivo.length(); start += longitudRegistro) {
            int end = Math.min(contenidoArchivo.length(), start + longitudRegistro);
            registros.add(contenidoArchivo.substring(start, end));
        }
        JavaRDD<String> registrosRDD = jsc.parallelize(registros);

        JavaRDD<Row> rowRDD = registrosRDD.map(linea -> {
            List<Object> values = new ArrayList<>();
            int startPosition = 0;
            for (EstructuraCampo campo : estructura.getEstructura()) {
                String rawValue = linea.substring(startPosition, startPosition + campo.getLongitud()).trim();
                Object value;
                switch (campo.getTipo()) {
                    case "int":
                        value = Integer.parseInt(rawValue);
                        break;
                    case "decimal":
                        if (rawValue.contains(",")) {
                            rawValue = rawValue.replace(",", ".");
                        }
                        value = Double.parseDouble(rawValue);
                        break;
                    case "string":
                    default:
                        value = rawValue;
                        break;
                }
                values.add(value);
                startPosition += campo.getLongitud();
            }
            return RowFactory.create(values.toArray());
        });

        List<StructField> fields = new ArrayList<>();

        String[] nombresDeColumnas = {"ID", "MARCA", "MODELO", "COLOR", "IMPORTE", "ANO"};

        for (int i = 0; i < estructura.getEstructura().size(); i++) {
            EstructuraCampo campo = estructura.getEstructura().get(i);
            DataType dataType;
            switch (campo.getTipo()) {
                case "int":
                    dataType = DataTypes.IntegerType;
                    break;
                case "decimal":
                    dataType = DataTypes.DoubleType;
                    break;
                case "string":
                default:
                    dataType = DataTypes.StringType;
                    break;
            }
            fields.add(DataTypes.createStructField(nombresDeColumnas[i], dataType, true));
        }
        StructType schema = DataTypes.createStructType(fields);

        Dataset<Row> df = spark.createDataFrame(rowRDD, schema);
        df.show(50);
    }
}
