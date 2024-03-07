package com.example.block16springcloud.application;

import org.example.dto.client.ClientInput;
import org.example.dto.client.output.ClientOutPutSimple;
import org.example.dto.client.output.ClientOutputComplete;

import java.util.List;

public interface ClientService {

    ClientOutputComplete addClient(ClientInput client);
    ClientOutputComplete getClient(int clientId);
    void deleteClient(int clientId);
    ClientOutputComplete updateClient(int clientId, ClientInput client);
    List<ClientOutPutSimple> getClients();

}
