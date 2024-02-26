package com.example.block7estadventa.application.implementation;

import com.example.block7estadventa.application.ClienteService;
import com.example.block7estadventa.controller.dto.cliente.ClienteInput;
import com.example.block7estadventa.domain.Cliente;
import com.example.block7estadventa.repository.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    private ObjectMapper objectMapper = new ObjectMapper();



    @Override
    public void saveCliente(ClienteInput cliente) {
        Cliente cliente1 = new Cliente(cliente);
        System.out.println("Cliente guardado: " + cliente1);
        clienteRepository.save(cliente1);

    }
    @KafkaListener(topics = "cliente", groupId = "group_cliente")
    public void listenClienteTopic(String message) {
        try {
            ClienteInput clienteInput = objectMapper.readValue(message, ClienteInput.class);
            saveCliente(clienteInput);

        } catch (Exception e) {
            // Manejar excepción
            e.printStackTrace();
        }
    }

    @Override
    public void updateCliente(ClienteInput cliente) {

    }
}
