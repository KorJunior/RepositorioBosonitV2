package com.example.block7estadventa.controller;

import com.example.block7estadventa.application.HistoricoVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Month;
import java.time.Year;


@RestController
@RequestMapping("/historicoventa")
public class HistoricoVentaController {

    @Autowired
    private HistoricoVentaService historicoVentaService;

    @GetMapping("/addMonthWithYear")
    public void addHistoricoVenta(@RequestParam int mes, @RequestParam int ano) {
        //Month month = Month.of(mes);
        //Year year = Year.of(ano);
        historicoVentaService.addHistoricoVentaMothWithYear(mes, ano);
    }
    @GetMapping("/addYear")
    public void addHistoricoVenta(@RequestParam int ano) {

        historicoVentaService.addHistoricoVentaYear(ano);
    }

}
