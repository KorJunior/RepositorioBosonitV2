package org.example.dto.dtoLineasDeFactura.lineasDeFacturaInputDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineasDeFacturaInputDto {
    private int idLinea;
    private int cantidad;
    private float precio;
    private float importe;
    private long idProducto;
    private int idFactura;
}
