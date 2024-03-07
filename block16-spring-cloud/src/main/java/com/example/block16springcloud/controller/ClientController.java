package com.example.block16springcloud.controller;

import com.example.block16springcloud.application.ClientService;
import lombok.RequiredArgsConstructor;
import org.example.dto.client.ClientInput;
import org.example.dto.client.output.ClientOutPutSimple;
import org.example.dto.client.output.ClientOutputComplete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passenger")
@RequiredArgsConstructor
public class ClientController {
    @Autowired
    ClientService clientService;

    @PostMapping("/createPassenger")
    public ClientOutputComplete addPassenger(@RequestBody ClientInput passenger) {
        return clientService.addClient(passenger);
    }

    @GetMapping("/getPassenger")
    public ClientOutputComplete getPassenger(@RequestParam int passengerId) {
        return clientService.getClient(passengerId);
    }

    @DeleteMapping("/deletePassenger")
    public void deletePassenger(@RequestParam int passengerId) {
        clientService.deleteClient(passengerId);
    }

    @PutMapping("/updatePassenger")
    public ClientOutputComplete updatePassenger(int passengerId, ClientInput passenger) {
        return clientService.updateClient(passengerId, passenger);
    }

    @GetMapping("/getPassengers")
    public List<ClientOutPutSimple> getPassengers() {
        return clientService.getClients();
    }

}
