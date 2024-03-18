package com.example.block18springiavectordatabase.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CocheInput {
    private String marca;
    private String modelo;
    private String color;
    private float importe;
    private int año;
}
