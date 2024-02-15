package com.example.block7jpaconrelacionesyllamadasentremicros.repository;

import com.example.block7jpaconrelacionesyllamadasentremicros.domain.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProvinciaRepository extends JpaRepository<Provincia, Integer> {

    @Query("SELECT p FROM Provincia p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT(:nombre, '%'))")
    Optional<List<Provincia>> findByNombre(@Param("nombre") String nombre);
}
