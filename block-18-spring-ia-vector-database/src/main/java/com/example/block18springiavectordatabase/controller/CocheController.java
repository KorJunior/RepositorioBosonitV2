package com.example.block18springiavectordatabase.controller;

import com.example.block18springiavectordatabase.application.CocheService;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/coche")
@RestController
public class CocheController {
    @Autowired
    private CocheService cocheService;
    private final Map<String, String> context;
    private final ChatClient chatClient;

    @Autowired
    public CocheController(OpenAiChatClient chatClient) {
        this.chatClient = chatClient;
        context = new HashMap<>();
    }

    @PostMapping("/ia")
    public String generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        String chatResponse;
        StringBuilder promptBuilder = new StringBuilder();

        if (!context.isEmpty()) {
            context.forEach((key, value) -> promptBuilder.append(key).append(": ").append(value).append("\n"));
        }else{
            /*
            List<Coche> coches=cocheService.getAllCoches();
            for (Coche coche:coches){
                promptBuilder.append(coche.getMarca()).append(" ").append(coche.getModelo()).append(" ").append(coche.getColor()).append(" ").append(coche.getImporte()).append(" ").append(coche.getAño()).append("\n");
            }
            context.put("coches_database",promptBuilder.toString());
            */

        }
        promptBuilder.append("Mensaje: ").append(message);
        chatResponse = chatClient.call(promptBuilder.toString());

        context.put(message, chatResponse);

        return chatResponse;
    }
    @PostMapping("/rellenarCoches")
    public String rellenarCoches() {
        cocheService.saveCoche();
        return "Mision cumplida";
    }
}
