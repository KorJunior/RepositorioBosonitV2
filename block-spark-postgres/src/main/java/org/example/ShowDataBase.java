package org.example;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.example.classdatabase.DbConnection;

public class ShowDataBase
{
    public static void main( String[] args )
    {
        DbConnection db = new DbConnection();
        Dataset<Row> dbSpark= db.configuracionDataBase();
        dbSpark.show();
    }


}
