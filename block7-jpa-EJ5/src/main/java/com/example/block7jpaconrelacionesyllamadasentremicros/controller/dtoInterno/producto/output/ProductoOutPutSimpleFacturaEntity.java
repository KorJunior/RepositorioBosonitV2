package com.example.block7jpaconrelacionesyllamadasentremicros.controller.dtoInterno.producto.output;

import com.example.block7jpaconrelacionesyllamadasentremicros.domain.Producto;
import org.example.dto.dtoProducto.productoOutPutDto.ProductoOutPutSimpleFactura;

public class ProductoOutPutSimpleFacturaEntity extends ProductoOutPutSimpleFactura {

    public ProductoOutPutSimpleFacturaEntity(Producto producto) {
        this.setId(producto.getIdProducto());
        this.setNombre(producto.getDescripcionProducto());
    }

}
