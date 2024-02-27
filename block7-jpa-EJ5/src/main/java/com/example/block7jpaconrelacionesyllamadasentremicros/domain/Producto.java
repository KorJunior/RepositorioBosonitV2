package com.example.block7jpaconrelacionesyllamadasentremicros.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.dtoProducto.productoInputDto.ProductoInputDto;
import org.example.dto.dtoProducto.productoOutPutDto.ProductoOutPutDtoComplete;
import org.example.dto.dtoProducto.productoOutPutDto.ProductoOutPutDtoSimple;


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
