package com.example.block16springcloud.controller;

import com.example.block16springcloud.application.ClientService;
import com.example.block16springcloud.application.TripService;
import lombok.RequiredArgsConstructor;
import org.example.dto.client.ClientInput;
import org.example.dto.trip.TripInput;
import org.example.dto.trip.output.TripOutputComplete;
import org.example.dto.trip.output.TripOutputSimple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
public class TripController {
    @Autowired
    ClientService clientService;
    @Autowired
    TripService tripService;


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
        return tripService.addTrip(trip);
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
