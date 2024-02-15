package com.example.block7jpaconrelacionesyllamadasentremicros.repository;

import com.example.block7jpaconrelacionesyllamadasentremicros.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, String>{

    @Query("SELECT c FROM Cliente c WHERE LOWER(c.nombre) LIKE LOWER(CONCAT(:nombre, '%'))")
    Optional<List<Cliente>> findByNombre(@Param("nombre") String nombre);


}
