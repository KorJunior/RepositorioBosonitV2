package com.example.block16ticket.controller;

import com.example.block16ticket.application.TripShopService;
import lombok.RequiredArgsConstructor;
import org.example.dto.tripShop.TripShopOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trip-shop")
@RequiredArgsConstructor
public class TripShopController {
    @Autowired
    TripShopInterface ticketInterface;
    @Autowired
    TripShopService tripShopService;

    @RequestMapping("/buy")
    public String buyTicket(@RequestParam int tripId) {
        TripShopOutput tripShop;
        tripShop =tripShopService.buyTicket(tripId);
        ticketInterface.sendTrip(tripShop);
        return "Ticket bought";
    }

}
