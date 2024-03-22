package org.example;

import com.github.javafaker.Faker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class GeneradorDeFichero {

    public static void main(String[] args) {
        Faker faker = new Faker(new Locale("es", "MX"));

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("vehiculos.txt"));


            for (int i = 0; i < 50; i++) {
                long id = faker.number().numberBetween(1L, 999999999L);
                String marca = faker.company().name();
                String modelo = faker.funnyName().name();
                String color = faker.color().name();
                float importe = (float) faker.number().randomDouble(2, 10000, 50000);
                int ano = faker.number().numberBetween(1990, 2022);

                bw.write(padRight(String.valueOf(id), 10));
                bw.write(padRight(marca, 20));
                bw.write(padRight(modelo, 20));
                bw.write(padRight(color, 10));
                bw.write(padRight(String.format("%.2f", importe), 9));
                bw.write(padRight(String.valueOf(ano), 5));

            }

            bw.close();
            System.out.println("Fichero generado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String padRight(String s, int n) {
        if (s.length() > n) {
            return s.substring(0, n);
        }
        return String.format("%-" + n + "s", s);
    }

}
