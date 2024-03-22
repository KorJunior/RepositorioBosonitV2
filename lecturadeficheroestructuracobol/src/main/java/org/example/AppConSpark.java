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

        JavaRDD<String> lineasArchivo = jsc.textFile(archivoDatos.getPath());

        JavaRDD<Row> rowRDD = lineasArchivo.flatMap(linea -> {
            List<Row> rows = new ArrayList<>();
            int totalRegistroLength = estructura.obtenerTamañodeBulto() ;
            int startPosition = 0;

            while (!linea.substring(startPosition).isEmpty()) {
                List<Object> values = new ArrayList<>();
                int currentPosition = startPosition;

                for (EstructuraCampo campo : estructura.getEstructura()) {
                    String rawValue = linea.substring(currentPosition, currentPosition + campo.getLongitud()).trim();
                    Object value = null;
                    switch (campo.getTipo()) {
                        case "int":
                            value = Integer.parseInt(rawValue);
                            break;
                        case "decimal":
                            rawValue = rawValue.replace(",", ".");
                            value = Double.parseDouble(rawValue);
                            break;
                        case "string":
                        default:
                            value = rawValue;
                            break;
                    }
                    values.add(value);
                    currentPosition += campo.getLongitud();
                }

                if (values.size() == estructura.getEstructura().size()) {
                    rows.add(RowFactory.create(values.toArray()));
                }
                startPosition += totalRegistroLength; 
            }

            return rows.iterator();
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
