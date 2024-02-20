package org.example.dto.dtoLineasDeFactura.lineasDeFacturaInputDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineasDeFacturaInputFactura {
    private int idProducto;
    private int cantidad;
    private int precio;
}
