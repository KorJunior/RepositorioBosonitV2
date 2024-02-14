package com.example.block7jpaconrelacionesyllamadasentremicros.domain;

import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoProducto.productoInputDto.ProductoInputDto;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoProducto.productoOutPutDto.ProductoOutPutDtoComplete;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoProducto.productoOutPutDto.ProductoOutPutDtoSimple;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue
    private Long idProducto;
    private String descripcionProducto;
    private float precioProducto;
    @OneToOne(mappedBy = "producto")
    private LineasDeFactura lineasDeFacturas;


    public Producto(ProductoInputDto productoInputDto) {
        this.descripcionProducto = productoInputDto.getDescripcionProducto();
        this.precioProducto = productoInputDto.getPrecioProducto();
    }

    public ProductoOutPutDtoComplete toProductorOutPutDtoComplete() {
        ProductoOutPutDtoComplete productoOutPutDtoComplete = new ProductoOutPutDtoComplete();
        productoOutPutDtoComplete.setIdProducto(this.idProducto);
        productoOutPutDtoComplete.setDescripcionProducto(this.descripcionProducto);
        productoOutPutDtoComplete.setPrecioProducto(this.precioProducto);
        productoOutPutDtoComplete.setLineasDeFacturas(this.lineasDeFacturas !=null ? this.lineasDeFacturas.toLineasDeFacturaOutPutSimple() : null);
        return productoOutPutDtoComplete;
    }

    public ProductoOutPutDtoSimple toProductoOutPutDtoSimple() {
        return new ProductoOutPutDtoSimple(this.descripcionProducto, this.precioProducto);
    }
}
