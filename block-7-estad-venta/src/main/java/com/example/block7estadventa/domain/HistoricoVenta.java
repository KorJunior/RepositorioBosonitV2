package com.example.block7estadventa.domain;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.Month;
import java.time.Year;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoVenta {

    @Id
    @GeneratedValue
    private int idHistoricoVentas;
    private String idCliente;
    private int mes;
    private int ano;
    @ElementCollection
    private List<Integer> cantidad;
    @ElementCollection
    private List<Long> idProducto;
    private float importeTotal;

}

