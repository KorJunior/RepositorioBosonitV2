package com.example.block7jpaconrelacionesyllamadasentremicros.application;


import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoProducto.productoInputDto.ProductoInputDto;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoProducto.productoOutPutDto.ProductoOutPutDtoComplete;

import java.util.List;

public interface ProductoService {
    ProductoOutPutDtoComplete getProducto(int id);
    ProductoOutPutDtoComplete addProducto(ProductoInputDto producto);
    ProductoOutPutDtoComplete updateProducto(ProductoInputDto producto);
    void deleteProducto(int id);
    List<ProductoOutPutDtoComplete> getAllProducto();
}
