package com.example.block16ticket.application;

import org.example.dto.tripShop.TripShopOutput;

public interface TripShopService {

    TripShopOutput saveTripShop(int id);

    TripShopOutput updateTripShop(int id);

    TripShopOutput buyTicket(int id);

}
