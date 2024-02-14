package com.example.block7jpaconrelacionesyllamadasentremicros.domain;

import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCliente.clienteOutput.ClienteOutPutDtoSimple;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoProvincia.ProvinciaInputDto;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoProvincia.provinciaOutPut.ProvinciaOutPutSimple;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoProvincia.provinciaOutPut.ProvinciaOutPutComplete;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Provincia {
    @Id
    @GeneratedValue
    private int codProvincia;
    private String nombre;
    @OneToMany(mappedBy = "provincia",fetch = FetchType.LAZY)
    private List<Cliente> clientes;

    public Provincia(ProvinciaInputDto provincia) {
        this.codProvincia = provincia.getCodProvincia();
        this.nombre = provincia.getNombre();
    }

    public ProvinciaOutPutComplete toProvinciaOutputDto() {
        ProvinciaOutPutComplete provinciaOutPutComplete = new ProvinciaOutPutComplete();
        provinciaOutPutComplete.setProvincia(this.codProvincia);
        provinciaOutPutComplete.setNombre(this.nombre);
        provinciaOutPutComplete.setClientes(convertirListaClientes(this.clientes));
        return provinciaOutPutComplete;

    }
    private List<ClienteOutPutDtoSimple> convertirListaClientes(List<Cliente> clientes) {
        return clientes.stream()
                .map(this::convertirCliente)
                .collect(Collectors.toList());
    }

    private ClienteOutPutDtoSimple convertirCliente(Cliente cliente) {
        return new ClienteOutPutDtoSimple(cliente);
    }


    public ProvinciaOutPutComplete toProvinciaOutPut() {
        ProvinciaOutPutComplete provinciaOutPutComplete = new ProvinciaOutPutComplete();
        provinciaOutPutComplete.setProvincia(this.codProvincia);
        provinciaOutPutComplete.setNombre(this.nombre);
        provinciaOutPutComplete.setClientes(this.clientes != null ? convertirListaClientes(this.clientes) : Collections.emptyList());

        return provinciaOutPutComplete;
    }


    public ProvinciaOutPutSimple toProvinciaOutPutSimple() {
        ProvinciaOutPutSimple provinciaOutPutSimple = new ProvinciaOutPutSimple();
        provinciaOutPutSimple.setNombre(this.nombre);
        return provinciaOutPutSimple;
    }


}
