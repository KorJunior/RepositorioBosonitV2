package com.example.block7jpaconrelacionesyllamadasentremicros.controller;

import com.example.block7jpaconrelacionesyllamadasentremicros.application.implementation.CabeceraDeFacturaServiceImpl;

import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCabeceraDeFactura.cabeceraDeFacturaInput.CabeceraDeFacturaInputDto;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCabeceraDeFactura.cabeceraDeFacturaInput.FacturaInputDto;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCabeceraDeFactura.cabeceraDeFacturaOutPutDto.CabeceraDeFacturaOutPutDtoComplete;

import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCabeceraDeFactura.cabeceraDeFacturaOutPutDto.FacturaOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cabeceradefacturas")
public class CabeceraDeFacturaController {

    @Autowired
    private CabeceraDeFacturaServiceImpl cabeceraDeFacturaService;

    @PostMapping
    public CabeceraDeFacturaOutPutDtoComplete addCabeceraDeFactura(@RequestBody CabeceraDeFacturaInputDto cabeceraDeFacturaInputDto) {
        return cabeceraDeFacturaService.addCabeceraDeFactura(cabeceraDeFacturaInputDto);
    }
    @GetMapping
    public List<CabeceraDeFacturaOutPutDtoComplete> getAllCabeceraDeFacturas() {
        return cabeceraDeFacturaService.getAllCabeceraDeFacturas();
    }

    @PostMapping("/insertarFactura")
    public FacturaOutput addFactura(@RequestBody FacturaInputDto facturaInputDto) {
        System.out.println(facturaInputDto);
         return cabeceraDeFacturaService.addFactura(facturaInputDto);

    }
}
