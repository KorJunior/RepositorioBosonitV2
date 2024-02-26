package com.example.block7estadventa.application;

import com.example.block7estadventa.controller.dto.cliente.ClienteInput;

public interface ClienteService {
    void saveCliente(ClienteInput cliente);
    void updateCliente(ClienteInput cliente);
}
