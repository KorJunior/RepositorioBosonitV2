package com.example.block18springiavectordatabase.domain;

import com.example.block18springiavectordatabase.controller.dto.CocheInput;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Coche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca;
    private String modelo;
    private String color;
    private float importe;
    private int año;

    public Coche(CocheInput coche) {
        this.marca = coche.getMarca();
        this.modelo = coche.getModelo();
        this.color = coche.getColor();
        this.importe = coche.getImporte();
        this.año = coche.getAño();
    }
}
