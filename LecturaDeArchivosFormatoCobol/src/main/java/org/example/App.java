package org.example;


import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;


/**
 * Hello world!
 */
public class App {
    static File archivoEstructura = new File("estructura.txt");
    static File archivoDatos = new File("vehiculos.txt");

    public static void main(String[] args) throws IOException {
        BufferedReader file = null;
        List<List<String>> listaDatos = new ArrayList<>();

        if (archivoEstructura.exists()) {
            try{
                Estructura estructura = new Estructura(archivoEstructura);
                file = new BufferedReader(new FileReader(archivoDatos));

                while (file.ready()){
                    String linea = file.readLine();
                    List<String> datos = new ArrayList<>();
                    int inicio = 0;
                    for (EstructuraCampo campo : estructura.getEstructura()) {
                        int fin = inicio + campo.getLongitud();
                        datos.add(linea.substring(inicio, fin));
                        inicio = fin;
                    }
                    listaDatos.add(datos);
                }
                System.out.println("Datos del archivo");
                for (List<String> datos : listaDatos) {
                    System.out.println(datos);
                }


                System.out.println(estructura.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                assert file != null;
                file.close();
            }

        } else {
            System.out.println("El archivo de la estructura no existe");
        }


    }

    private static void detectorDeCaracteres() {

    }
}
