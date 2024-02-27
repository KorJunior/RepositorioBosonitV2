package com.example.block7jpaconrelacionesyllamadasentremicros.controller;

import com.example.block7jpaconrelacionesyllamadasentremicros.application.LineasDeFacturaService;
import org.example.dto.dtoLineasDeFactura.lineasDeFacturaInputDto.LineasDeFacturaInputDto;
import org.example.dto.dtoLineasDeFactura.lineasDeFacturaOutPut.LineasDeFacturaOutPutComplete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lineasdefactura")
public class LineasDeFacturaController {

    @Autowired
    private LineasDeFacturaService lineasDeFacturaService;
    @GetMapping("/{id}")
    public LineasDeFacturaOutPutComplete getLineasDeFacutra(@PathVariable int id) {
        return lineasDeFacturaService.getLineasDeFacutra(id);
    }
    @PostMapping
    public LineasDeFacturaOutPutComplete addLineasDeFacutura(@RequestBody LineasDeFacturaInputDto lineasDeFacturaInputDto) {
        return lineasDeFacturaService.addLineasDeFacutura(lineasDeFacturaInputDto);
    }

    @GetMapping
    public List<LineasDeFacturaOutPutComplete> getAllLineasDeFactura() {
        return lineasDeFacturaService.getAllLineasDeFactura();
    }

}
