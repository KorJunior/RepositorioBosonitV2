package com.example.block7estadventa.application;

import com.example.block7estadventa.controller.dto.producto.ProductoInput;

public interface ProductoService {
    void saveProducto(ProductoInput producto);
    void updateProducto(ProductoInput producto);
}
