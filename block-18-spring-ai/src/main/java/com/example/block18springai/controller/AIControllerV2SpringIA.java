package com.example.block18springai.controller;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;
@RequestMapping("/aiv2")
@RestController
public class AIControllerV2SpringIA {

    private final Map<String, String> context;
    private final ChatClient chatClient;

    @Autowired
    public AIControllerV2SpringIA(OpenAiChatClient chatClient) {
        this.chatClient = chatClient;
        context = new HashMap<>();
    }

    @GetMapping("/message")
    public String generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        String chatResponse;
        StringBuilder promptBuilder = new StringBuilder();

        if (!context.isEmpty()) {
            context.forEach((key, value) -> promptBuilder.append(key).append(": ").append(value).append("\n"));
        }
        promptBuilder.append("Mensaje: ").append(message);

        chatResponse = chatClient.call(promptBuilder.toString());

        context.put(message, chatResponse);

        return chatResponse;
    }
}
