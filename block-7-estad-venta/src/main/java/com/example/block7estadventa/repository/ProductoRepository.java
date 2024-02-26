package com.example.block7estadventa.repository;


import com.example.block7estadventa.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository  extends JpaRepository<Producto, Integer> {
}
