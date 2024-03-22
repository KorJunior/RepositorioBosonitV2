package org.example;

import java.io.Serializable;

class EstructuraCampo implements Serializable {
    private String tipo;
    private int longitud;

    public EstructuraCampo(String tipo, int longitud) {
        this.tipo = tipo;
        this.longitud = longitud;
    }

    public String getTipo() {
        return tipo;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    @Override
    public String toString() {
        return "EstructuraCampo{" +
                "tipo='" + tipo + '\'' +
                ", longitud=" + longitud +
                '}';
    }
}
