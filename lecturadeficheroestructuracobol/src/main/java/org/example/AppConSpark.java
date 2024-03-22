package org.example;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class AppConSpark {
    static File archivoEstructura = new File("estructura.txt");
    static File archivoDatos = new File("vehiculos.txt");

    public static void main(String[] args) {
        List<List<String>> listaDatos = new ArrayList<>();
        SparkSession spark = SparkSession.builder()
                .appName("Lector Fichero Cobol")
                .master("local")
                .getOrCreate();

        Dataset<Row> df = spark.read().textFile(archivoDatos.getPath()).toDF();
        System.out.println("Lectura de datos");




    }
}
