package org.example.dto.dtoProducto.productoOutPutDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoOutPutSimpleFactura {
    private Long id;
    private String nombre;

//    public ProductoOutPutSimpleFactura(Producto producto) {
//        this.id = producto.getIdProducto();
//        this.nombre = producto.getDescripcionProducto();
//    }
}
