package com.example.block16springcloud.repository;

import com.example.block16springcloud.domain.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Integer> {
}
