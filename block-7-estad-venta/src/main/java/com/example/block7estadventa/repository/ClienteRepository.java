package com.example.block7estadventa.repository;

import com.example.block7estadventa.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository  extends JpaRepository<Cliente, String> {

}
