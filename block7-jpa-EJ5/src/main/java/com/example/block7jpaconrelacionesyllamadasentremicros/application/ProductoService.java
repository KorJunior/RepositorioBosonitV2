package com.example.block7jpaconrelacionesyllamadasentremicros.application;




import org.example.dto.dtoProducto.productoInputDto.ProductoInputDto;
import org.example.dto.dtoProducto.productoOutPutDto.ProductoOutPutDtoComplete;

import java.util.List;

public interface ProductoService {
    ProductoOutPutDtoComplete getProducto(int id);
    ProductoOutPutDtoComplete addProducto(ProductoInputDto producto);
    ProductoOutPutDtoComplete updateProducto(ProductoInputDto producto);
    void deleteProducto(int id);
    List<ProductoOutPutDtoComplete> getAllProducto();
    List<ProductoOutPutDtoComplete> getProductoByNombre(String nombre);
}
