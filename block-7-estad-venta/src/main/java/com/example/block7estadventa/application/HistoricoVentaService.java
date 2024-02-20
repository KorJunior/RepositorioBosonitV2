package com.example.block7estadventa.application;

import com.example.block7estadventa.controller.dto.HistoricoVentasOutPut;
import com.example.block7estadventa.domain.HistoricoVenta;

import java.time.Month;
import java.time.Year;
import java.util.List;

public interface HistoricoVentaService {

    List<HistoricoVentasOutPut> obtenerHistoricoVentaMesConAno(int mes, int ano);
    List<HistoricoVentasOutPut> obtenerHistoricoVentaAno(int ano);
}
