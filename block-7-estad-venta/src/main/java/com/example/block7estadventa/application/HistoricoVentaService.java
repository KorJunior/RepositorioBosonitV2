package com.example.block7estadventa.application;

import com.example.block7estadventa.domain.HistoricoVenta;

import java.time.Month;
import java.time.Year;
import java.util.List;

public interface HistoricoVentaService {

    HistoricoVenta addHistoricoVentaMothWithYear(int mes, int ano);
    List<HistoricoVenta> addHistoricoVentaYear(int ano);
}
