package com.example.block16springcloud.application.implement;

import com.example.block16springcloud.application.ClientService;
import com.example.block16springcloud.domain.Client;
import com.example.block16springcloud.domain.Trip;
import com.example.block16springcloud.exception.MyException;
import com.example.block16springcloud.repository.ClientRepository;
import com.example.block16springcloud.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.example.dto.client.ClientInput;
import org.example.dto.client.output.ClientOutPutSimple;
import org.example.dto.client.output.ClientOutputComplete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImplement implements ClientService {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    TripRepository tripRepository;

    @Override
    public ClientOutputComplete addClient(ClientInput client) {
            Client client1 = new Client(client);
            clientRepository.save(client1);

            return client1.toClientOutPutComplete();
    }

    @Override
    public ClientOutputComplete getClient(int clientId) {
        Optional<Client> client = clientRepository.findById(clientId);
        if (client.isEmpty()) {
            throw new MyException("Client not found");
        }else{
            return client.get().toClientOutPutComplete();
        }


    }

    @Override
    public void deleteClient(int clientId) {
        Optional<Client> client = clientRepository.findById(clientId);
        if (client.isEmpty()) {
            throw new MyException("Client not found");
        }else{
            clientRepository.delete(client.get());
        }
    }

    @Override
    public ClientOutputComplete updateClient(int clientId, ClientInput client) {
        Optional<Client> client1 = clientRepository.findById(clientId);
        if (client1.isEmpty()) {
            throw new MyException("Client not found");
        }else{
            Client client2 = client1.get();
            if(client.getNombre() != null) {
                client2.setNombre(client.getNombre());
            }
            if(client.getApellido() != null) {
                client2.setApellido(client.getApellido());
            }
            if(client.getEdad() > 0) {
                client2.setEdad(client.getEdad());
            }
            if(client.getEmail() != null) {
                client2.setEmail(client.getEmail());
            }
            if(client.getTelefono() > 0) {
                client2.setTelefono(client.getTelefono());
            }

            clientRepository.save(client2);
            return client2.toClientOutPutComplete();
        }
    }

    @Override
    public List<ClientOutPutSimple> getClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map(Client::toClientOutPutSimple).toList();
    }
}
