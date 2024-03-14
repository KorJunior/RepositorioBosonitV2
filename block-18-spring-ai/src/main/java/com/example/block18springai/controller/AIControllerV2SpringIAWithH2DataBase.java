package com.example.block18springai.controller;

import com.example.block18springai.application.ChatEntityService;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/aiv3")
@RestController
public class AIControllerV2SpringIAWithH2DataBase {

    ChatEntityService chatEntityService;
    private final ChatClient chatClient;

    @Autowired
    public AIControllerV2SpringIAWithH2DataBase(OpenAiChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/message")
    public String generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        String chatResponse;
        StringBuilder promptBuilder = new StringBuilder();
        String chatCompleto=chatEntityService.getAllChat();

        if (!chatCompleto.isEmpty()) {
            promptBuilder.append(chatCompleto);
        }
        promptBuilder.append("Mensaje: ").append(message);

        chatResponse = chatClient.call(promptBuilder.toString());

        chatEntityService.saveChatEntity(message, chatResponse);

        return chatResponse;
    }
}
