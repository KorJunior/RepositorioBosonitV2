package com.example.block16ticket.application.implement;

import com.example.block16ticket.application.TripShopService;
import com.example.block16ticket.controller.TripShopInterface;
import com.example.block16ticket.domain.TripShop;
import com.example.block16ticket.exception.MyException;
import com.example.block16ticket.rabbitmq.RabbitMQConsumer;
import com.example.block16ticket.repository.TripShopRepository;
import org.example.dto.trip.output.TripOutputComplete;
import org.example.dto.tripShop.TripShopOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TripShopImplement implements TripShopService {
    @Autowired
    TripShopRepository tripShopRepository;
    @Autowired
    TripShopInterface tripShopInterface;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void consumerMessage(TripShopOutput message) {
        LOGGER.info(String.format("Consuming message: %s", message));
        TripShop tripShop = new TripShop(message);
        tripShopRepository.save(tripShop);
    }
    @Override
    public TripShopOutput saveTripShop(int id) {

        return null;
    }

    @Override
    public TripShopOutput updateTripShop(int id) {
        return null;
    }

    @Override
    public TripShopOutput buyTicket(int id) {
        Optional<TripShop> tripShopDatabase = tripShopRepository.findById(id);
        TripShop tripShop;
        if (tripShopDatabase.isEmpty()) {
            throw new MyException("Trip not found");
        }else{
            tripShop = tripShopDatabase.get();
            if (tripShop.getQuantity() >= 40) {
                throw new MyException("No tickets available");
            }
            tripShop.setQuantity(tripShop.getQuantity()+1);
            tripShopInterface.sendTrip(tripShop.toTripShopOutput());
            return tripShop.toTripShopOutput();
        }

    }
}
