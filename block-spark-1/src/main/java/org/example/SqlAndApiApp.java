package org.example;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;

public class SqlAndApiApp {
    public static void main(String[] args) {
        SqlAndApiApp app = new SqlAndApiApp();
        app.start();
    }

    private void start() {
        SparkSession spark = SparkSession.builder()
                .appName("Simple SQL")
                .master("local")
                .getOrCreate();

        Dataset<Row> df = spark.read().format("csv")
                .option("header", true)
                .load("data/populationbycountry19802010millions.csv");

        for (int i = 1980; i <= 2010; i++) {
            if (!(i == 1980 || i == 1981 || i == 2010)) {
                df = df.drop(df.col(i + ""));
            }
        }
        df = df.withColumn(
                "evolution",
                functions.expr("round((`2010` - `1980`) * 1000000)"));
        df.createOrReplaceTempView("geodata");
        Dataset<Row> negativeEvolutionDf =
                spark.sql(
                        "SELECT * FROM geodata "
                                + "WHERE Country IS NOT NULL AND evolution<=0 "
                                + "ORDER BY evolution "
                                + "LIMIT 25");
        negativeEvolutionDf.show(15, false);
        Dataset<Row> moreThanAMillionDf =
                spark.sql(
                        "SELECT * FROM geodata "
                                + "WHERE Country IS NOT NULL AND evolution>999999 "
                                + "ORDER BY evolution DESC "
                                + "LIMIT 25");
        moreThanAMillionDf.show(15, false);
    }
}
