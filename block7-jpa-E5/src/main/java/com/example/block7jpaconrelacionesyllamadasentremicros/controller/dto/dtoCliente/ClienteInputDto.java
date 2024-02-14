package com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteInputDto {
    private String dni;
    private String nombre;
    private String direccion;
    private int codProvincia;
}
