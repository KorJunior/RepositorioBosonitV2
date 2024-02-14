package com.example.block7jpaconrelacionesyllamadasentremicros.application.implementation;

import com.example.block7jpaconrelacionesyllamadasentremicros.application.ProductoService;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoProducto.productoInputDto.ProductoInputDto;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoProducto.productoOutPutDto.ProductoOutPutDtoComplete;
import com.example.block7jpaconrelacionesyllamadasentremicros.domain.Producto;
import com.example.block7jpaconrelacionesyllamadasentremicros.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;
    @Override
    public ProductoOutPutDtoComplete getProducto(int id) {
        return null;
    }

    @Override
    public ProductoOutPutDtoComplete addProducto(ProductoInputDto productoInputDto) {
        Producto producto = new Producto(productoInputDto);
        productoRepository.save(producto);
        return producto.toProductorOutPutDtoComplete();
    }

    @Override
    public ProductoOutPutDtoComplete updateProducto(ProductoInputDto producto) {
        return null;
    }

    @Override
    public void deleteProducto(int id) {

    }

    @Override
    public List<ProductoOutPutDtoComplete> getAllProducto() {
        return productoRepository.findAll().stream()
                .map(Producto :: toProductorOutPutDtoComplete)
                .collect(Collectors.toList());
    }
}
