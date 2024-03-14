package com.example.block18springai.repository;

import com.example.block18springai.domain.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatEntityRepository extends JpaRepository<ChatEntity, Integer> {
}
