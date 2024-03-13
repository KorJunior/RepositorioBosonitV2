package com.example.block18springai.controller;

import org.springframework.ai.chat.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/aiv2")
public class AIControllerV2 {
    private final ChatClient chatClient;

    @Autowired
    public AIControllerV2(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/message")
    public Map<String, Object> generate(@RequestParam(value = "message", defaultValue = "¿Donde queda Mali?") String message) {
        return Map.of("generation", chatClient.call(message));
    }
}
