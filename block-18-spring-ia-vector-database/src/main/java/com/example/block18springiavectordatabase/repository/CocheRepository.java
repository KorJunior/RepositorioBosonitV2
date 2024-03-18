package com.example.block18springiavectordatabase.repository;

import com.example.block18springiavectordatabase.domain.Coche;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CocheRepository extends JpaRepository<Coche, Integer> {
}
