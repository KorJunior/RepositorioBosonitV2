package org.example.dto.dtoLineasDeFactura.lineasDeFacturaOutPut;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.dtoProducto.productoOutPutDto.ProductoOutPutSimpleFactura;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineasDeFacturaOutPutFactura {
    private ProductoOutPutSimpleFactura producto;
    private int cantidad;
    private float precio;


}
