package org.example.dto.dtoProducto.productoInputDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.dtoLineasDeFactura.lineasDeFacturaOutPut.LineasDeFacturaOutPutComplete;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoInputDto {

    private Long idProducto;
    private String descripcionProducto;
    private float precioProducto;

    private LineasDeFacturaOutPutComplete lineasDeFacturas;


}
