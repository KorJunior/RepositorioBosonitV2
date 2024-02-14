package com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoLineasDeFactura.lineasDeFacturaInputDto;

import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoProducto.productoInputDto.ProductoInputDto;
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
