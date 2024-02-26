package com.example.block7jpaconrelacionesyllamadasentremicros.application.implementation;

import com.example.block7jpaconrelacionesyllamadasentremicros.application.ProductoService;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCliente.clienteOutput.ClienteOutPutHistorico;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoProducto.productoInputDto.ProductoInputDto;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoProducto.productoOutPutDto.ProductoOutPutDtoComplete;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoProducto.productoOutPutDto.ProductoOutPutHistorico;
import com.example.block7jpaconrelacionesyllamadasentremicros.domain.CabeceraDeFactura;
import com.example.block7jpaconrelacionesyllamadasentremicros.domain.Producto;
import com.example.block7jpaconrelacionesyllamadasentremicros.repository.CabeceraDeFacturaRepository;
import com.example.block7jpaconrelacionesyllamadasentremicros.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private CabeceraDeFacturaRepository cabeceraDeFacturaRepository;
    @Autowired
    KafkaTemplate<String, ProductoOutPutHistorico> productoKafkaTemplate;

    @Override
    public ProductoOutPutDtoComplete getProducto(int id) {
        return productoRepository.findById((long) id)
                .orElseThrow(() -> new NoSuchElementException("No existe el producto"))
                .toProductorOutPutDtoComplete();
    }

    @Override
    public ProductoOutPutDtoComplete addProducto(ProductoInputDto productoInputDto) {
        Producto producto = new Producto(productoInputDto);

        productoRepository.save(producto);
        ProductoOutPutHistorico productoOutPutHistorico = new ProductoOutPutHistorico(producto);
        productoKafkaTemplate.send("producto", productoOutPutHistorico);
        return producto.toProductorOutPutDtoComplete();
    }

    @Override
    public ProductoOutPutDtoComplete updateProducto(ProductoInputDto producto) {
        Optional<Producto> producto1 = productoRepository.findById(producto.getIdProducto());
        ProductoOutPutHistorico productoOutPutHistorico;
        Producto producto2;
        if (producto1.isPresent()) {
            producto2 = producto1.get();
            producto2.setDescripcionProducto(producto.getDescripcionProducto());
            producto2.setPrecioProducto(producto.getPrecioProducto());
            productoOutPutHistorico= new ProductoOutPutHistorico(producto2);
            productoKafkaTemplate.send("producto", productoOutPutHistorico);
            productoRepository.save(producto2);
        } else {
            throw new NoSuchElementException("No existe el producto");
        }

        return producto2.toProductorOutPutDtoComplete();

    }

    @Override
    public void deleteProducto(int id) {
        Optional<List<CabeceraDeFactura>> cabeceraDeFactura = cabeceraDeFacturaRepository.findByCodigoDeProducto((long) id);

        if (cabeceraDeFactura.isPresent()) {
            throw new IllegalArgumentException("No se puede borrar el producto porque tiene facturas asociadas");
        }else{
            cabeceraDeFacturaRepository.deleteById(id);
        }

    }

    @Override
    public List<ProductoOutPutDtoComplete> getAllProducto() {
        return productoRepository.findAll().stream()
                .map(Producto :: toProductorOutPutDtoComplete)
                .collect(Collectors.toList());
    }

    public List<ProductoOutPutDtoComplete> getProductoByNombre(String nombre) {
        Optional<List<Producto>> productos = productoRepository.findByNombre(nombre);

        return productos.orElseThrow(() -> new NoSuchElementException("No existe el producto"))
                .stream()
                .map(Producto::toProductorOutPutDtoComplete)
                .collect(Collectors.toList());
    }
}
