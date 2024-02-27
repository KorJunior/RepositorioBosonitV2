package com.example.block7jpaconrelacionesyllamadasentremicros.controller.dtoInterno.lineasFactura.output;

import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dtoInterno.producto.output.ProductoOutPutSimpleFacturaEntity;
import com.example.block7jpaconrelacionesyllamadasentremicros.domain.LineasDeFactura;
import org.example.dto.dtoLineasDeFactura.lineasDeFacturaOutPut.LineasDeFacturaOutPutFactura;

public class LineasDeFacturaOutPutFacturaEntity extends LineasDeFacturaOutPutFactura {


    public LineasDeFacturaOutPutFacturaEntity(LineasDeFactura lineasDeFactura) {
        this.setProducto(new ProductoOutPutSimpleFacturaEntity(lineasDeFactura.getProducto()));
        this.setCantidad(lineasDeFactura.getCantidad());
        this.setPrecio(lineasDeFactura.getPrecio());

    }
}
