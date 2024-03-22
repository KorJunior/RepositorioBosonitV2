package org.example;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class AppSinSpark {
    static File archivoEstructura = new File("estructura.txt");
    static File archivoDatos = new File("vehiculos.txt");

    public static void main(String[] args) {
        List<List<String>> listaDatos = new ArrayList<>();

        if (archivoEstructura.exists() && archivoDatos.exists()) {
            FileReader fileReader = null;
            try {
                Estructura estructura = new Estructura(archivoEstructura);
                int tamañoBytes = estructura.obtenerTamañoEnBytes();
                char[] buffer = new char[tamañoBytes];
                fileReader = new FileReader(archivoDatos);
                List<String> datosLocales;
                int charsLeidos;
                while ((charsLeidos = fileReader.read(buffer, 0, buffer.length)) != -1) {
                    String linea = new String(buffer, 0, charsLeidos);
                    datosLocales = new ArrayList<>();
                    int inicio = 0;
                    for (EstructuraCampo campo : estructura.getEstructura()) {
                        int fin = inicio + campo.getLongitud();
                        //Esto es simplemente para que no se pase del tamaño de la línea
                        if (fin > linea.length()) {
                            fin = linea.length();
                        }
                        datosLocales.add(linea.substring(inicio, fin));
                        inicio = fin;
                    }
                    listaDatos.add(datosLocales);
                }

                System.out.println("Datos del archivo:");
                for (List<String> datos : listaDatos) {
                    System.out.println(datos);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        } else {
            System.out.println("El archivo de estructura o datos no existe");
        }
    }
}
