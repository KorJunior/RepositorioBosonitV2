package com.example.block7jpaconrelacionesyllamadasentremicros.repository;

import com.example.block7jpaconrelacionesyllamadasentremicros.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Query("SELECT p FROM Producto p WHERE LOWER(p.descripcionProducto) LIKE LOWER(CONCAT(:nombre, '%'))")
    Optional<List<Producto>> findByNombre(@Param("nombre") String nombre);
}
