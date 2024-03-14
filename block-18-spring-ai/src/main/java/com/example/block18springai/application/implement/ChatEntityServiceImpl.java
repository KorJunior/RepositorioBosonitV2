package com.example.block18springai.application.implement;

import com.example.block18springai.application.ChatEntityService;
import com.example.block18springai.domain.ChatEntity;
import com.example.block18springai.repository.ChatEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatEntityServiceImpl implements ChatEntityService {
    @Autowired
    ChatEntityRepository chatEntityRepository;

    @Override
    public void saveChatEntity(String message, String response) {
        ChatEntity chatEntity = new ChatEntity(message, response);
        chatEntityRepository.save(chatEntity);
    }

    @Override
    public String getAllChat() {
        StringBuilder chat = new StringBuilder();
        chatEntityRepository.findAll().forEach(chatEntity -> chat.append(chatEntity.toString()));
        return chat.toString();
    }
}
