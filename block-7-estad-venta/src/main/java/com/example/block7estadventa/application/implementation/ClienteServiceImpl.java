package com.example.block7estadventa.application.implementation;

import com.example.block7estadventa.application.ClienteService;
import com.example.block7estadventa.controller.dto.cliente.ClienteInput;
import com.example.block7estadventa.domain.Cliente;
import com.example.block7estadventa.repository.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.dtoCliente.clienteOutput.ClienteOutPutHistorico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;




    @Override
    public void saveCliente(ClienteInput cliente) {
        Cliente cliente1 = new Cliente(cliente);
        System.out.println("Cliente guardado: " + cliente1);
        clienteRepository.save(cliente1);

    }
    @KafkaListener(topics = "cliente", groupId = "group_cliente")
    public void listenClienteTopic(ClienteOutPutHistorico  message) {

        if (message instanceof ClienteOutPutHistorico clienteObtenido) {
            ClienteInput clienteInput = new ClienteInput(clienteObtenido);
            saveCliente(clienteInput);
        }

    }

    @Override
    public void updateCliente(ClienteInput cliente) {

    }
}
