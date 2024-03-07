package com.example.block16springcloud.repository;

import com.example.block16springcloud.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
