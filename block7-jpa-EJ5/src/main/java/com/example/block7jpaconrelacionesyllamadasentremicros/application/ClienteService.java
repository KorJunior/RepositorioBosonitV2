package com.example.block7jpaconrelacionesyllamadasentremicros.application;




import org.example.dto.dtoCliente.ClienteInputDto;
import org.example.dto.dtoCliente.clienteOutput.ClienteOutputDtoComplete;

import java.util.List;

public interface ClienteService {
    ClienteOutputDtoComplete getCliente(String id);
    ClienteOutputDtoComplete addCliente(ClienteInputDto clienteInputDto);
    ClienteOutputDtoComplete updateCliente(ClienteInputDto clienteInputDto);
    void deleteCliente(String id);
    List<ClienteOutputDtoComplete> getAllCliente();
    List<ClienteOutputDtoComplete> findByName(String nombre);
}
