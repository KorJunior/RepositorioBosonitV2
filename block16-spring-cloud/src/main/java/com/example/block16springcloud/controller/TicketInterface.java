package com.example.block16springcloud.controller;

import org.example.dto.ticket.TicketOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ticket-service")
public interface TicketInterface {
    @PostMapping("/api/receiveTicket")
    TicketOutput sendTicket(@RequestBody TicketOutput ticket);
}
