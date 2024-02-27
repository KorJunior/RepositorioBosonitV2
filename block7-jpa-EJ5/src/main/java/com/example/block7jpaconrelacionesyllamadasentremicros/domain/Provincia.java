package com.example.block7jpaconrelacionesyllamadasentremicros.domain;


import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dtoInterno.cliente.output.ClienteOutPutDtoSimpleEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.dtoCliente.clienteOutput.ClienteOutPutDtoSimple;
import org.example.dto.dtoProvincia.ProvinciaInputDto;
import org.example.dto.dtoProvincia.provinciaOutPut.ProvinciaOutPutComplete;
import org.example.dto.dtoProvincia.provinciaOutPut.ProvinciaOutPutSimple;

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
        return new ClienteOutPutDtoSimpleEntity(cliente);
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
