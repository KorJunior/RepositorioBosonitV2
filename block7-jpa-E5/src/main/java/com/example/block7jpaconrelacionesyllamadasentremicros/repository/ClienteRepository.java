package com.example.block7jpaconrelacionesyllamadasentremicros.repository;

import com.example.block7jpaconrelacionesyllamadasentremicros.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, String>{
}
