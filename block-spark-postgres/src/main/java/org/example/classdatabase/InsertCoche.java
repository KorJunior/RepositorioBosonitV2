package org.example.classdatabase;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.types.StructField;

import java.util.*;

public class InsertCoche {
    private SparkSession spark;
    private Properties connectionProperties;

    public InsertCoche() {
        this.spark = SparkSession.builder()
                .appName("Insertar Coches")
                .master("local")
                .getOrCreate();

        this.connectionProperties = new Properties();
        connectionProperties.put("user", "junior");
        connectionProperties.put("password", "1234");
    }

    public void insertarCoches( Map<String, Object> datos) {
        List<StructField> fields = new ArrayList<>();
        for (String campo : datos.keySet()) {
            if (campo.equals("id") || campo.equals("ano")) {
                fields.add(DataTypes.createStructField(campo, DataTypes.LongType, true));
            } else if (campo.equals("importe")) {
                fields.add(DataTypes.createStructField(campo, DataTypes.FloatType, true));
            } else {
                fields.add(DataTypes.createStructField(campo, DataTypes.StringType, true));
            }
        }
        StructType schema = DataTypes.createStructType(fields);

        List<Row> rows = new ArrayList<>();
        System.out.println(Arrays.toString(datos.values().toArray()));
        rows.add(RowFactory.create(datos.values().toArray()));

        Dataset<Row> df = spark.createDataFrame(rows, schema);

        df.write()
                .mode("append")
                .jdbc("jdbc:postgresql://localhost:5432/concesionario", "public.coche", this.connectionProperties);
    }
}