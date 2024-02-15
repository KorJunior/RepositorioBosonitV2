package com.example.block7jpaconrelacionesyllamadasentremicros.controller;

import com.example.block7jpaconrelacionesyllamadasentremicros.application.ClienteService;
import com.example.block7jpaconrelacionesyllamadasentremicros.application.implementation.ClienteServiceImpl;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCliente.ClienteInputDto;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCliente.clienteOutput.ClienteOutputDtoComplete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @GetMapping("/{id}")
    public ClienteOutputDtoComplete getCliente(@PathVariable String id) {
        return clienteService.getCliente(id);
    }

    @PostMapping
    public ClienteOutputDtoComplete addCliente(@RequestBody ClienteInputDto clienteInputDto) {
        return clienteService.addCliente(clienteInputDto);
    }
    @GetMapping("/nombre/{nombre}")
    public List<ClienteOutputDtoComplete> findByName(@PathVariable String nombre) {
        return clienteService.findByName(nombre);
    }

    @PutMapping("/{id}")
    public ClienteOutputDtoComplete updateCliente(@PathVariable String id, @RequestBody ClienteInputDto clienteInputDto) {
        return clienteService.updateCliente(clienteInputDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable String id) {
        clienteService.deleteCliente(id);
    }

    @GetMapping
    public List<ClienteOutputDtoComplete> getAllClientes() {
        return clienteService.getAllCliente();
    }
}
