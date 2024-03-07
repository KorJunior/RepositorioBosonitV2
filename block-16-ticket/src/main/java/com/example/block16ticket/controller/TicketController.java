package com.example.block16ticket.controller;

import org.example.dto.ticket.TicketOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TicketController {
    @PostMapping("/receiveTicket")
    public ResponseEntity<Void> receiveTicket(@RequestBody TicketOutput ticket) {
        System.out.println("Ticket received: " + ticket.toString());

        return ResponseEntity.ok().build();
    }
}

