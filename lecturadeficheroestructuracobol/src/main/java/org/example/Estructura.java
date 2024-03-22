package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Estructura implements Serializable {
    private List<EstructuraCampo> estructura = new ArrayList<>();

    public Estructura(File estructura) {
        obtenerEstructura(estructura);
    }

    private void obtenerEstructura(File estructura) {
        try {
            FileReader fr = new FileReader(estructura);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {

                String[] partes = linea.split("[()]");
                if (partes.length > 1) {
                    String tipo = partes[0].trim();
                    String longitud = partes[1].trim();
                    int longitudInt = 0;


                    if (linea.contains("V")) {
                        String[] longitudDecimal = partes[2].split("V");
                        int parteEntera = Integer.parseInt(partes[1]);
                        int decimales = longitudDecimal[1].length();
                        longitudInt = parteEntera + decimales + 2;
                        this.estructura.add(new EstructuraCampo("decimal", longitudInt));
                    } else {
                        longitudInt = Integer.parseInt(longitud);
                        if (tipo.startsWith("9")) {
                            this.estructura.add(new EstructuraCampo("int", longitudInt + 1));
                        } else if (tipo.startsWith("X")) {
                            this.estructura.add(new EstructuraCampo("string", longitudInt));
                        }
                    }
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    int obtenerTamañodeBulto() {
        int tamaño = 0;
        for (EstructuraCampo campo : estructura) {
            tamaño += campo.getLongitud();
        }
        return tamaño;
    }

    @Override
    public String toString() {
        return "Estructura{" +
                "estructura=" + estructura +
                '}';
    }

    public List<EstructuraCampo> getEstructura() {
        return estructura;
    }

    public void setEstructura(List<EstructuraCampo> estructura) {
        this.estructura = estructura;
    }
}



