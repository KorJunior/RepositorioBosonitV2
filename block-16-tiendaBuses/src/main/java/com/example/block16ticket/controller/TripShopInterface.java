package com.example.block16ticket.controller;



import org.example.dto.tripShop.TripShopOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "trip-service")
public interface TripShopInterface {
    @PostMapping("/trip/receiveTrip")
    TripShopOutput sendTrip(@RequestBody TripShopOutput ticket);
}
