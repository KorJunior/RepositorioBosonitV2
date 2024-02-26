package com.example.block7jpaconrelacionesyllamadasentremicros.controller;

import com.example.block7jpaconrelacionesyllamadasentremicros.application.ProductoService;
import com.example.block7jpaconrelacionesyllamadasentremicros.application.implementation.ProductoServiceImpl;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoProducto.productoInputDto.ProductoInputDto;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoProducto.productoOutPutDto.ProductoOutPutDtoComplete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;
    @PostMapping
    public ProductoOutPutDtoComplete addProducto(@RequestBody ProductoInputDto productoInputDto) {
        return productoService.addProducto(productoInputDto);
    }
    @GetMapping("/nombre/{nombre}")
    public List<ProductoOutPutDtoComplete> getProductoByNombre(@PathVariable String nombre) {
        return productoService.getProductoByNombre(nombre);
    }
    @PutMapping
    public ProductoOutPutDtoComplete updateProducto(@RequestBody ProductoInputDto producto) {
        return productoService.updateProducto(producto);
    }
    @GetMapping("/{id}")
    public ProductoOutPutDtoComplete getProducto(@PathVariable int id) {
        return productoService.getProducto(id);
    }

    @GetMapping
    public List<ProductoOutPutDtoComplete> getAllProducto() {
        return productoService.getAllProducto();
    }
}
