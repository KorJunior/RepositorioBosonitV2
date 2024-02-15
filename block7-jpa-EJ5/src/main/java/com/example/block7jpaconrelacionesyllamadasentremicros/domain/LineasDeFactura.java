package com.example.block7jpaconrelacionesyllamadasentremicros.domain;

import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoLineasDeFactura.lineasDeFacturaInputDto.LineasDeFacturaInputDto;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoLineasDeFactura.lineasDeFacturaInputDto.LineasDeFacturaInputFactura;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoLineasDeFactura.lineasDeFacturaOutPut.LineasDeFacturaOutPutComplete;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoLineasDeFactura.lineasDeFacturaOutPut.LineasDeFacturaOutPutSimple;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineasDeFactura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLinea;
    private int cantidad;
    private float precio;
    private float importe;
    @OneToOne
    @JoinColumn(name = "idProducto", referencedColumnName = "idProducto")
    private Producto producto;
    @ManyToOne
    @JoinColumn (name = "idFactura")
    private CabeceraDeFactura cabeceraDeFactura;

    public LineasDeFactura(LineasDeFacturaInputDto lineasDeFacturaInputDto) {
        this.cantidad = lineasDeFacturaInputDto.getCantidad();
        this.precio = lineasDeFacturaInputDto.getPrecio();
        this.importe = lineasDeFacturaInputDto.getImporte();
    }

    public LineasDeFactura(LineasDeFacturaInputFactura lineasDeFactura) {
        this.cantidad = lineasDeFactura.getCantidad();
        this.precio = lineasDeFactura.getPrecio();

    }

    public LineasDeFacturaOutPutComplete toLineasDeFacturaOutPutComplete() {
        LineasDeFacturaOutPutComplete lineasDeFacturaOutPutComplete = new LineasDeFacturaOutPutComplete();
        lineasDeFacturaOutPutComplete.setIdLinea(this.idLinea);
        lineasDeFacturaOutPutComplete.setCantidad(this.cantidad);
        lineasDeFacturaOutPutComplete.setPrecio(this.precio);
        lineasDeFacturaOutPutComplete.setImporte(this.importe);
        lineasDeFacturaOutPutComplete.setProducto(this.producto.toProductoOutPutDtoSimple());
        lineasDeFacturaOutPutComplete.setCabeceraDeFactura(this.cabeceraDeFactura.toCabeceraDeFacturaOutPutDtoSimple());
        return lineasDeFacturaOutPutComplete;
    }

    public LineasDeFacturaOutPutSimple toLineasDeFacturaOutPutSimple() {
        return new LineasDeFacturaOutPutSimple(this.cantidad, this.precio, this.importe);
    }


}
