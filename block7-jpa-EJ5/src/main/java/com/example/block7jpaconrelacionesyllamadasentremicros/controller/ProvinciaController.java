package com.example.block7jpaconrelacionesyllamadasentremicros.controller;

import com.example.block7jpaconrelacionesyllamadasentremicros.application.ProvinciaService;

import org.example.dto.dtoProvincia.ProvinciaInputDto;
import org.example.dto.dtoProvincia.provinciaOutPut.ProvinciaOutPutComplete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provincia")
public class ProvinciaController {

    @Autowired
    private ProvinciaService provinciaService;

    @PostMapping
    public ProvinciaOutPutComplete addCliente(@RequestBody ProvinciaInputDto provinciaInputDto) {
        return provinciaService.addProvincia(provinciaInputDto);
    }
    @GetMapping("/nombre/{nombre}")
    public List<ProvinciaOutPutComplete> getProvinciaByNombre(@PathVariable String nombre) {
        return provinciaService.getProvinciaByNombre(nombre);
    }

    @GetMapping
    public List<ProvinciaOutPutComplete> getAllProvincia() {
        return provinciaService.getAllProvincia();
    }
}


