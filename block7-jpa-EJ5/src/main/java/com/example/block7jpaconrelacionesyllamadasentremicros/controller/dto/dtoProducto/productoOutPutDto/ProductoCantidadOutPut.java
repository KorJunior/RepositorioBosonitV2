package com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoProducto.productoOutPutDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoCantidadOutPut {
        private Long producto;
        private Integer cantidad;
        private int idLineaDeFactura;

        // Getters, setters y constructor


}
