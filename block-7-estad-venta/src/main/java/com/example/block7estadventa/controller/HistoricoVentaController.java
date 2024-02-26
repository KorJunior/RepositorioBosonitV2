package com.example.block7estadventa.controller;

import com.example.block7estadventa.application.HistoricoVentaService;
import com.example.block7estadventa.controller.dto.historico.HistoricoVentasOutPut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.example.dto.dtoCliente.ClienteInputDto;

@RestController
@RequestMapping("/historicoventa")
public class HistoricoVentaController {
    @Autowired
    private HistoricoVentaService historicoVentaService;

    @GetMapping("/obtenerHistorico")
    public List<HistoricoVentasOutPut> obtenerHistoricoVenta(@RequestParam int ano, @RequestParam(required = false) Integer mes) {
        ClienteInputDto clienteInputDto = new ClienteInputDto();

        if (mes != null) {
            return historicoVentaService.obtenerHistoricoVentaMesConAno(mes, ano);
        } else {
            return historicoVentaService.obtenerHistoricoVentaAno(ano);
        }
    }
}
