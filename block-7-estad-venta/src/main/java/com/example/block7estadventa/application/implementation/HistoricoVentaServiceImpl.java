package com.example.block7estadventa.application.implementation;

import com.example.block7estadventa.application.HistoricoVentaService;
import com.example.block7estadventa.domain.HistoricoVenta;
import com.example.block7estadventa.repository.HistoricoVentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


import java.time.Month;
import java.time.Year;
import java.util.*;

@Service
public class HistoricoVentaServiceImpl implements HistoricoVentaService {

    @Autowired
    private HistoricoVentaRepository historicoVentaRepository;

    @Transactional
    @Override
    public HistoricoVenta addHistoricoVentaMothWithYear(int mes, int ano) {
        HistoricoVenta historicoVenta = new HistoricoVenta();
        List<Map<String, Object>> result;
        Optional<HistoricoVenta> historicoVentaOptional;

        historicoVentaOptional = historicoVentaRepository.findByMesAndAno(mes, ano);
        if (historicoVentaOptional.isPresent()) {
            return historicoVentaOptional.get();
        }

        result = obtenerFacturasMesAno(mes, ano);


        for (Map<String, Object> map : result) {
            guardarHistorico(ano, historicoVenta, map, mes);
        }
        System.out.println("historicoVenta = " + historicoVenta);

        return historicoVenta;
    }


    private List<Map<String, Object>> obtenerFacturasMesAno(int mes, int ano) {
        String url = "http://localhost:8080/cabeceradefacturas/getFacturaByMonthAndYear?mes=" + mes + "&ano=" + ano;
        WebClient webClient = WebClient.create();
        HistoricoVenta historicoVenta = new HistoricoVenta();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Map<String, Object>>>() {
                })
                .block();
    }

    @Override
    public List<HistoricoVenta> addHistoricoVentaYear(int ano) {
        List<Map<String, Object>> result = obtenerFacturaAno(ano);
        List<HistoricoVenta> historicosGuardados = new ArrayList<>();

        for (Map<String, Object> map : result) {
            Date fecha = (Date) map.get("fecha");
            Calendar cal = Calendar.getInstance();
            cal.setTime(fecha);
            int mes = cal.get(Calendar.MONTH) + 1;

            Optional<HistoricoVenta> historicoVentaOptional = historicoVentaRepository.findByMesAndAno(mes, ano);

            if (historicoVentaOptional.isEmpty()) {
                HistoricoVenta historicoVenta = new HistoricoVenta();
                guardarHistorico(ano, historicoVenta, map, mes);
                historicosGuardados.add(historicoVenta);
            }
        }

        return historicosGuardados;
    }


    private void guardarHistorico(int ano, HistoricoVenta historicoVenta, Map<String, Object> map, int mes) {
        historicoVenta.setMes(mes);
        historicoVenta.setAno(ano);
        historicoVenta.setIdCliente((String) map.get("cliente"));

        if (map.get("totalFactura") instanceof Number) {
            historicoVenta.setImporteTotal(((Number) map.get("totalFactura")).floatValue());
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> productos = (List<Map<String, Object>>) map.get("productos");
        List<Long> idProductos = new ArrayList<>();
        List<Integer> cantidades = new ArrayList<>();

        for (Map<String, Object> producto : productos) {
            Object productoId = producto.get("producto");
            Object cantidad = producto.get("cantidad");

            if (productoId instanceof Number) {
                idProductos.add(((Number) productoId).longValue());
            }

            if (cantidad instanceof Number) {
                cantidades.add(((Number) cantidad).intValue());
            }
        }

        historicoVenta.setIdProducto(idProductos);
        historicoVenta.setCantidad(cantidades);
        historicoVentaRepository.save(historicoVenta);
    }

    private static List<Map<String, Object>> obtenerFacturaAno(int ano) {
        String url = "http://localhost:8080/cabeceradefacturas/getFacturaByYear/" + ano;
        WebClient webClient = WebClient.create();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Map<String, Object>>>() {
                })
                .block();
    }


}
