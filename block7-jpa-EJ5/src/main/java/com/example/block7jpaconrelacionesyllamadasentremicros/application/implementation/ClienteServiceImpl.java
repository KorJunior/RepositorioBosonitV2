package com.example.block7jpaconrelacionesyllamadasentremicros.application.implementation;

import com.example.block7jpaconrelacionesyllamadasentremicros.application.ClienteService;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCabeceraDeFactura.cabeceraDeFacturaOutPutDto.FacturaOutPutHistorico;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCliente.ClienteInputDto;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCliente.clienteOutput.ClienteOutPutHistorico;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCliente.clienteOutput.ClienteOutputDtoComplete;
import com.example.block7jpaconrelacionesyllamadasentremicros.domain.CabeceraDeFactura;
import com.example.block7jpaconrelacionesyllamadasentremicros.domain.Cliente;
import com.example.block7jpaconrelacionesyllamadasentremicros.domain.Provincia;
import com.example.block7jpaconrelacionesyllamadasentremicros.exception.MyException;
import com.example.block7jpaconrelacionesyllamadasentremicros.repository.CabeceraDeFacturaRepository;
import com.example.block7jpaconrelacionesyllamadasentremicros.repository.ClienteRepository;
import com.example.block7jpaconrelacionesyllamadasentremicros.repository.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl  implements ClienteService {
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ProvinciaRepository provinciaRepository;
    @Autowired
    CabeceraDeFacturaRepository cabeceraDeFacturaRepository;
    @Autowired
    KafkaTemplate<String, ClienteOutPutHistorico> clienteKafkaTemplate;

    @Override
    public ClienteOutputDtoComplete getCliente(String id) {
        return clienteRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No existe el cliente")).toClienteOutputDto();
    }

    @Override
    public ClienteOutputDtoComplete addCliente(ClienteInputDto clienteInputDto) {
        Cliente cliente = new Cliente(clienteInputDto);
        ClienteOutPutHistorico clienteOutPutHistorico;
        int codProvincia= clienteInputDto.getCodProvincia();
        if (codProvincia!=0){
            Optional<Provincia> o1= provinciaRepository.findById(codProvincia);
            if (o1.isPresent()){
                Provincia provincia = o1.get();
                cliente.setProvincia(provincia);

                clienteOutPutHistorico = new ClienteOutPutHistorico(cliente);
                clienteKafkaTemplate.send("cliente", clienteOutPutHistorico);
                clienteRepository.save(cliente);
                return cliente.toClienteOutputDto();
            }else{
                throw new MyException("Provincia no encontrada");
            }

        }else{
            throw new MyException("Provincia no encontrada");
        }
    }

    public List<ClienteOutputDtoComplete> findByName(String nombre) {
        Optional<List<Cliente>> clientes = clienteRepository.findByNombre(nombre);

        return clientes.orElseThrow(() -> new NoSuchElementException("No existe el cliente"))
                .stream()
                .map(Cliente::toClienteOutputDto)
                .collect(Collectors.toList());

    }

    @Override
    public ClienteOutputDtoComplete updateCliente(ClienteInputDto clienteInputDto) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteInputDto.getDni());
        if (cliente.isPresent()) {
            Cliente cliente1 = new Cliente(clienteInputDto);
            if (clienteInputDto.getCodProvincia() > 0) {
                Optional<Provincia> provincia = provinciaRepository.findById(clienteInputDto.getCodProvincia());
                if (provincia.isPresent()) {
                    cliente1.setProvincia(provincia.get());
                } else {
                    throw new RuntimeException("No existe la provincia");
                }
            } else {
                throw new RuntimeException("No existe la provincia");
            }
            clienteRepository.save(cliente1);
            clienteKafkaTemplate.send("cliente", new ClienteOutPutHistorico(cliente1));
            return cliente1.toClienteOutputDto();
        } else {
            throw new RuntimeException("No existe el cliente");
        }

    }

    @Override
    public void deleteCliente(String id) {
        Optional<List<CabeceraDeFactura>> cabeceraDeFactura = cabeceraDeFacturaRepository.findByDniCliente(id);
        if (cabeceraDeFactura.isEmpty()) {
            throw new RuntimeException("No se puede borrar el cliente porque tiene facturas asociadas");
        }else{
            clienteRepository.deleteById(id);
        }
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
