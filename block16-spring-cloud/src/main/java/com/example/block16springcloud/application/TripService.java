package com.example.block16springcloud.application;


import org.example.dto.trip.TripInput;
import org.example.dto.trip.output.TripOutputComplete;
import org.example.dto.trip.output.TripOutputSimple;

import java.util.List;

public interface TripService {

    boolean verify(int tripId);
    TripOutputComplete updateStatusTrip(int tripId,String tripStatus);
    int count(int tripId);
    TripOutputComplete addPassenger(int tripId, int passengerId);
    TripOutputComplete addTrip(TripInput tripInput);
    TripOutputComplete getTrip(int tripId);
    void deleteTrip(int tripId);
    TripOutputComplete updateTrip(int tripId, TripInput tripInput);
    List<TripOutputSimple> getTrips();

}
