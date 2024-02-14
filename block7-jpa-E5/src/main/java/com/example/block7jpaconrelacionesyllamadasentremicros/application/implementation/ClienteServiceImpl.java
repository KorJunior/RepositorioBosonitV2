package com.example.block7jpaconrelacionesyllamadasentremicros.application.implementation;

import com.example.block7jpaconrelacionesyllamadasentremicros.application.ClienteService;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCliente.ClienteInputDto;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCliente.clienteOutput.ClienteOutputDtoComplete;
import com.example.block7jpaconrelacionesyllamadasentremicros.domain.Cliente;
import com.example.block7jpaconrelacionesyllamadasentremicros.domain.Provincia;
import com.example.block7jpaconrelacionesyllamadasentremicros.repository.ClienteRepository;
import com.example.block7jpaconrelacionesyllamadasentremicros.repository.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl  implements ClienteService {
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ProvinciaRepository provinciaRepository;

    @Override
    public ClienteOutputDtoComplete getCliente(String id) {
        return null;
    }

    @Override
    public ClienteOutputDtoComplete addCliente(ClienteInputDto clienteInputDto) {
        Cliente cliente = new Cliente(clienteInputDto);
        int codProvincia= clienteInputDto.getCodProvincia();
        if (codProvincia!=0){
            Optional<Provincia> o1= provinciaRepository.findById(codProvincia);
            if (o1.isPresent()){
                Provincia provincia = o1.get();
                cliente.setProvincia(provincia);

                clienteRepository.save(cliente);
                return cliente.toClienteOutputDto();
            }else{
                throw new RuntimeException("Provincia no encontrada");
            }

        }else{
            throw new RuntimeException("Provincia no encontrada");
        }
    }

    @Override
    public ClienteOutputDtoComplete updateCliente(ClienteInputDto clienteInputDto) {
        return null;
    }

    @Override
    public void deleteCliente(String id) {

    }

    @Override
    public List<ClienteOutputDtoComplete> getAllCliente() {
        return toClienteClienteOutPut();

    }

    private List<ClienteOutputDtoComplete> toClienteClienteOutPut() {
        return clienteRepository.findAll().stream().
                map(Cliente::toClienteOutputDto).
                collect(Collectors.toList());

    }
}
