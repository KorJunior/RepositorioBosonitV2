package com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoLineasDeFactura.lineasDeFacturaOutPut;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineasDeFacturaOutPutSimple {

    private int cantidad;
    private float precio;
    private float importe;
}
