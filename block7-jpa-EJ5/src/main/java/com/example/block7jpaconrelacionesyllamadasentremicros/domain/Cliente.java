package com.example.block7jpaconrelacionesyllamadasentremicros.domain;

import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCliente.ClienteInputDto;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCliente.clienteOutput.ClienteOutPutDtoSimple;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCliente.clienteOutput.ClienteOutputDtoComplete;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    private String dni;
    private String nombre;
    private String direccion;
    @ManyToOne
    @JoinColumn(name = "codProvincia")
    private Provincia provincia;
    @OneToMany(mappedBy = "cliente")
    private List<CabeceraDeFactura> cabeceraDeFacturas;

    public Cliente(ClienteInputDto clienteInputDto) {
        this.dni = clienteInputDto.getDni();
        this.nombre = clienteInputDto.getNombre();
        this.direccion = clienteInputDto.getDireccion();
    }

    public ClienteOutputDtoComplete toClienteOutputDto() {
        return new ClienteOutputDtoComplete(
                this.dni,
                this.nombre,
                this.direccion,
                this.provincia.toProvinciaOutPutSimple(),
                this.cabeceraDeFacturas != null ? this.cabeceraDeFacturas.stream()
                        .map(CabeceraDeFactura::toCabeceraDeFacturaOutPutDtoSimple)
                        .toList()
                        : Collections.emptyList()
        );
    }


    public ClienteOutPutDtoSimple toClienteOutputDtoSimple() {
        return new ClienteOutPutDtoSimple(this);
    }


}
