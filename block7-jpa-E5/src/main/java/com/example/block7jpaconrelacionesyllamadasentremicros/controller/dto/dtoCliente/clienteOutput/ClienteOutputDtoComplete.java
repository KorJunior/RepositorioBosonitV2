package com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCliente.clienteOutput;

import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCabeceraDeFactura.cabeceraDeFacturaOutPutDto.CabeceraDeFacturaOutPutDtoSimple;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoProvincia.provinciaOutPut.ProvinciaOutPutSimple;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteOutputDtoComplete {
    private String dni;
    private String nombre;
    private String direccion;
    private ProvinciaOutPutSimple codProvincia;
    private List<CabeceraDeFacturaOutPutDtoSimple> cabeceraDeFacturas;




}
