package com.example.block7estadventa.application.implementation;

import com.example.block7estadventa.application.ProductoService;
import com.example.block7estadventa.controller.dto.cliente.ClienteInput;
import com.example.block7estadventa.controller.dto.producto.ProductoInput;
import com.example.block7estadventa.domain.Producto;
import com.example.block7estadventa.repository.ProductoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void saveProducto(ProductoInput producto) {
        Producto producto1 = new Producto(producto);

        productoRepository.save(producto1);

    }
    @KafkaListener(topics = {"producto"}, groupId = "group_producto")
    public void listenProductTopic(String message) {
        try {
            ProductoInput productoInput = objectMapper.readValue(message, ProductoInput.class);
                saveProducto(productoInput);

        } catch (Exception e) {
            // Manejar excepción
            e.printStackTrace();
        }
    }


    @Override
    public void updateProducto(ProductoInput producto) {
    }
}
