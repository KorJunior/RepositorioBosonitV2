package org.example;



import org.example.classdatabase.InsertCoche;
import java.util.HashMap;
import java.util.Map;

public class AñadirDatos {
    public static void main(String[] args) {
        InsertCoche insertCoche = new InsertCoche();
        Map<String, Object> datos = new HashMap<>();
        datos.put("id", 2003L);
        datos.put("marca", "Cars 1");
        datos.put("modelo", "Corolla");
        datos.put("color", "Rojo");
        datos.put("importe", 20000.0f);
        datos.put("ano", 2020L);
        insertCoche.insertarCoches( datos);
    }
}
