package org.example.classdatabase;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.Properties;

public class DbConnection {
    public Dataset<Row> configuracionDataBase() {
        DbFunctions db = new DbFunctions();
        db.connect_to_db();

        SparkSession spark = SparkSession.builder()
                .appName("Concesionario SQL")
                .master("local")
                .getOrCreate();

        Properties connectionProperties = new Properties();
        connectionProperties.put("user", "junior");
        connectionProperties.put("password", "1234");

        Dataset<Row> df = spark.read()
                .option("header", true)
                .jdbc("jdbc:postgresql://localhost:5432/concesionario", "public.coche", connectionProperties);
        df.createOrReplaceTempView("vehiculos");
        return df;
    }
}
