package org.example.dto.dtoProducto.productoOutPutDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.dtoLineasDeFactura.lineasDeFacturaOutPut.LineasDeFacturaOutPutSimple;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoOutPutDtoComplete {

    private Long idProducto;
    private String descripcionProducto;
    private float precioProducto;

    private LineasDeFacturaOutPutSimple lineasDeFacturas;


}
