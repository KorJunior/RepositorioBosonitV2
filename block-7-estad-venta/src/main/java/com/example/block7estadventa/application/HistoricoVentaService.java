package com.example.block7estadventa.application;

import com.example.block7estadventa.controller.dto.historico.HistoricoVentasOutPut;

import java.util.List;

public interface HistoricoVentaService {

    List<HistoricoVentasOutPut> obtenerHistoricoVentaMesConAno(int mes, int ano);
    List<HistoricoVentasOutPut> obtenerHistoricoVentaAno(int ano);
}
