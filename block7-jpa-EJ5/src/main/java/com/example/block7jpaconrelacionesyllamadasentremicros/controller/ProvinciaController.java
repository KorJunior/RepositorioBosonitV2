package com.example.block7jpaconrelacionesyllamadasentremicros.controller;

import com.example.block7jpaconrelacionesyllamadasentremicros.application.implementation.ProvinciaServiceImpl;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoProvincia.ProvinciaInputDto;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoProvincia.provinciaOutPut.ProvinciaOutPutComplete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provincia")
public class ProvinciaController {

    @Autowired
    private ProvinciaServiceImpl provinciaService;

    @PostMapping
    public ProvinciaOutPutComplete addCliente(@RequestBody ProvinciaInputDto provinciaInputDto) {
        return provinciaService.addProvincia(provinciaInputDto);
    }

    @GetMapping
    public List<ProvinciaOutPutComplete> getAllProvincia() {
        return provinciaService.getAllProvincia();
    }
}


