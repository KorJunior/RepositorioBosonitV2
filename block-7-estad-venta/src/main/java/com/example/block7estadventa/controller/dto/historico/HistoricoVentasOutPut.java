package com.example.block7estadventa.controller.dto.historico;

import com.example.block7estadventa.domain.HistoricoVenta;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoVentasOutPut {
    private String nombreCliente;
    private int mes;
    private int ano;
    private String producto;
    private int cantidad;
    private float importeTotal;

    public HistoricoVentasOutPut(HistoricoVenta historicoVenta) {
        this.nombreCliente = historicoVenta.getIdCliente();
        this.mes = historicoVenta.getMes();
        this.ano = historicoVenta.getAno();
        this.cantidad = historicoVenta.getCantidad();
        this.importeTotal = historicoVenta.getImporteTotal();
    }
}
