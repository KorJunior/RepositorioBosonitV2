package com.example.block16springcloud.controller;

import com.example.block16springcloud.application.ClientService;
import com.example.block16springcloud.application.TripService;
import com.example.block16springcloud.domain.Trip;
import com.example.block16springcloud.rabbit_mq.publisher.RabbitMQProducer;
import lombok.RequiredArgsConstructor;
import org.example.dto.client.ClientInput;
import org.example.dto.trip.TripInput;
import org.example.dto.trip.output.TripOutputComplete;
import org.example.dto.trip.output.TripOutputSimple;
import org.example.dto.tripShop.TripShopOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
public class TripController {

    @Autowired
    ClientService clientService;
    @Autowired
    TripService tripService;
    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    @PostMapping("/receiveTrip")
    public ResponseEntity<Void> receiveTrip(@RequestBody TripShopOutput ticket) {
        System.out.println("Trip received: " + ticket.toString());
        tripService.guardarTrip(ticket);
        rabbitMQProducer.sendMessage(ticket);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/addPassenger")
    public TripOutputComplete addPassenger(@RequestParam int tripId, @RequestParam int passengerId) {
        return tripService.addPassenger(tripId, passengerId);
    }

    @GetMapping("/count")
    public int count(@RequestParam int tripId) {
        return tripService.count(tripId);
    }
    @GetMapping("/verify")
    public boolean verify(@RequestParam int tripId) {
        return tripService.verify(tripId);
    }

    @PutMapping
    public TripOutputComplete updateStatusTrip(@RequestParam int tripId,@RequestParam String tripStatus) {
        return tripService.updateStatusTrip(tripId,tripStatus);
    }

    @PostMapping("/createTrip")
    public TripOutputComplete addTrip(@RequestBody TripInput trip) {
        TripOutputComplete tripOutputComplete=tripService.addTrip(trip);
        rabbitMQProducer.sendMessage(new TripShopOutput(tripOutputComplete));

        return tripOutputComplete;
    }
    @GetMapping("/getTrip")
    public TripOutputComplete getTrip(@RequestParam int tripId) {
        return tripService.getTrip(tripId);
    }
    @DeleteMapping("/deleteTrip")
    public void deleteTrip(@RequestParam int tripId) {
        tripService.deleteTrip(tripId);
    }
    @PutMapping("/updateTrip")
    public TripOutputComplete updateTrip(@RequestParam int tripId, @RequestBody TripInput trip) {
        return tripService.updateTrip(tripId, trip);
    }
    @GetMapping("/getTrips")
    public List<TripOutputSimple> getTrips() {
        return tripService.getTrips();
    }




}
