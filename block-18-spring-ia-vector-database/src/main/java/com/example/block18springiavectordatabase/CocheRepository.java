package com.example.block18springiavectordatabase;

import com.example.block18springiavectordatabase.domain.Coche;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CocheRepository implements JpaRepository<Coche, Integer> {
}
