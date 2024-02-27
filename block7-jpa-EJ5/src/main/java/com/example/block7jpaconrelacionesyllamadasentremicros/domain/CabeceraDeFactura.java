package com.example.block7jpaconrelacionesyllamadasentremicros.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.dtoCabeceraDeFactura.cabeceraDeFacturaInput.CabeceraDeFacturaInputDto;
import org.example.dto.dtoCabeceraDeFactura.cabeceraDeFacturaInput.FacturaInputDto;
import org.example.dto.dtoCabeceraDeFactura.cabeceraDeFacturaOutPutDto.CabeceraDeFacturaOutPutDtoComplete;
import org.example.dto.dtoCabeceraDeFactura.cabeceraDeFacturaOutPutDto.CabeceraDeFacturaOutPutDtoSimple;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CabeceraDeFactura {
    @GeneratedValue
    @Id
    private int idFactura;
    private Date fechaFactura;
    private float importeTotal;
    @ManyToOne
    @JoinColumn(name = "dni")
    private Cliente cliente;
    @OneToMany(mappedBy = "cabeceraDeFactura")
    private List<LineasDeFactura> lineasDeFactura;


    public CabeceraDeFactura(CabeceraDeFacturaInputDto cabeceraDeFacturaInputDto) {
        this.idFactura = cabeceraDeFacturaInputDto.getIdFactura();
        this.fechaFactura = cabeceraDeFacturaInputDto.getFechaFactura();
    }

    public CabeceraDeFactura(FacturaInputDto factura) {
        this.fechaFactura = factura.getFechaFactura();
    }

    public CabeceraDeFacturaOutPutDtoComplete toCabeceraDeFacturaOutPutDtoComplete() {
        return new CabeceraDeFacturaOutPutDtoComplete(this.idFactura, this.fechaFactura, this.importeTotal, this.cliente.toClienteOutputDtoSimple()
        ,lineasDeFactura != null ? lineasDeFactura.stream().map(LineasDeFactura::toLineasDeFacturaOutPutSimple).toList() : Collections.emptyList());
    }
    public CabeceraDeFacturaOutPutDtoSimple toCabeceraDeFacturaOutPutDtoSimple() {
        return new CabeceraDeFacturaOutPutDtoSimple(this.fechaFactura, this.importeTotal, this.cliente.toClienteOutputDtoSimple());
    }
}
