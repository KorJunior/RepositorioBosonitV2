package com.example.block7jpaconrelacionesyllamadasentremicros.repository;

import com.example.block7jpaconrelacionesyllamadasentremicros.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
