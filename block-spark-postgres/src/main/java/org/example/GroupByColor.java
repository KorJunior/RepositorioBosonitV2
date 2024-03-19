package org.example;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.example.classdatabase.DbConnection;

public class GroupByColor {
    public static void main(String[] args) {
        DbConnection db = new DbConnection();
        Dataset<Row> dbSpark = db.configuracionDataBase();

        Dataset<Row> groupedData = dbSpark.groupBy("color")
                .avg("importe")
                .withColumnRenamed("avg(precio)", "importe_promedio");

        groupedData.show();
    }
}
