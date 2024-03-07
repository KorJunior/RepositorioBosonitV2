package com.example.block16springcloud.application.implement;

import com.example.block16springcloud.application.TripService;
import com.example.block16springcloud.domain.Client;
import com.example.block16springcloud.domain.Trip;
import com.example.block16springcloud.exception.MyException;
import com.example.block16springcloud.rabbit_mq.publisher.RabbitMQProducer;
import com.example.block16springcloud.repository.ClientRepository;
import com.example.block16springcloud.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.example.dto.trip.TripInput;
import org.example.dto.trip.output.TripOutputComplete;
import org.example.dto.trip.output.TripOutputSimple;
import org.example.dto.tripShop.TripShopOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TripServiceImplement implements TripService {
    @Autowired
    TripRepository tripRepository;
    @Autowired
    ClientRepository clientRepository;



    @Override
    public boolean verify(int tripId) {
        Optional<Trip> tripDataBase = tripRepository.findById(tripId);
        if (tripDataBase.isPresent()) {
            int count = tripDataBase.get().getPassenger().size();
            if (count < 40) {
                return true;
            }else{
                return false;
            }
        }else{
            throw new MyException("Trip not found");
        }
    }

    @Override
    public TripOutputComplete updateStatusTrip(int tripId, String tripStatus) {
        Optional<Trip> tripDataBase = tripRepository.findById(tripId);
        if(tripDataBase.isPresent()){
            Trip trip = tripDataBase.get();
            trip.setStatus(tripStatus);
            tripRepository.save(trip);
            return trip.toTripOutputComplete();
        }else{
            throw new MyException("Trip not found");
        }
    }

    @Override
    public int count(int tripId) {
        Optional<Trip> tripDataBase = tripRepository.findById(tripId);
        return tripDataBase.map(trip -> trip.getPassenger().size()).orElse(0);
    }

    @Override
    public TripOutputComplete addPassenger(int tripId, int passengerId) {
        Optional<Trip> tripDataBase = tripRepository.findById(tripId);
        Optional<Client> passengerDataBase = clientRepository.findById(passengerId);
        Client passenger;
        Trip trip;;

        if (tripDataBase.isPresent()) {
            if (passengerDataBase.isPresent()) {
                passenger = passengerDataBase.get();
                passenger.setTrip(tripDataBase.get());
                trip = tripDataBase.get();
                trip.setQuantityPassenger(trip.getQuantityPassenger() + 1);
                tripRepository.save(trip);
                clientRepository.save(passenger);


                return trip.toTripOutputComplete();
            } else {
                throw new MyException("Passenger not found");
            }
        }else{
            throw new MyException("Trip not found");
        }
    }

    @Override
    public TripOutputComplete addTrip(TripInput tripInput) {
        Trip trip = new Trip(tripInput);
        tripRepository.save(trip);

        return trip.toTripOutputComplete();
    }

    @Override
    public TripOutputComplete getTrip(int tripId) {
        Optional<Trip> tripDataBase= tripRepository.findById(tripId);
        return tripDataBase.map(Trip::toTripOutputComplete).orElse(null);
    }

    @Override
    public void deleteTrip(int tripId) {
        Optional<Trip> tripDataBase= tripRepository.findById(tripId);
        tripDataBase.ifPresent(tripRepository::delete);
    }

    @Override
    public TripOutputComplete updateTrip(int tripId, TripInput tripInput) {
        Optional<Trip> tripDataBase = tripRepository.findById(tripId);
        if(tripDataBase.isPresent()){
            Trip trip = tripDataBase.get();
            if(tripInput.getOrigin() != null) {
                trip.setOrigin(tripInput.getOrigin());
            }
            if(tripInput.getDestination() != null) {
                trip.setDestination(tripInput.getDestination());
            }
            if(tripInput.getDepartureDate() != null) {
                trip.setDepartureDate(tripInput.getDepartureDate());
            }
            if(tripInput.getArrivalDate() != null) {
                trip.setArrivalDate(tripInput.getArrivalDate());
            }
            if(tripInput.getStatus() != null) {
                trip.setStatus(tripInput.getStatus());
            }
            tripRepository.save(trip);
            return trip.toTripOutputComplete();
        }else{
            return null;
        }
    }


    @Override
    public List<TripOutputSimple> getTrips() {
        List<Trip> tripsDataBase = tripRepository.findAll();
        return tripsDataBase.stream().map(Trip::toTripOutputSimple).toList();
    }

    @Override
    public void guardarTrip(TripShopOutput trip) {
        Optional<Trip> tripDataBase = tripRepository.findById(trip.getIdTrip());

        if (tripDataBase.isPresent()) {
            Trip trip1 = tripDataBase.get();
            trip1.setQuantityPassenger(trip.getQuantity());
            tripRepository.save(trip1);
        }else{
            throw new MyException("Trip not found");
        }
    }
}
