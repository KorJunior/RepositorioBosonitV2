package com.example.block7estadventa.domain;

import com.example.block7estadventa.controller.dto.producto.ProductoInput;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Producto {
    @Id
    private long id;
    private String nombre;

    public Producto(ProductoInput producto) {
        this.id = producto.getId();
        this.nombre = producto.getNombre();
    }
}
