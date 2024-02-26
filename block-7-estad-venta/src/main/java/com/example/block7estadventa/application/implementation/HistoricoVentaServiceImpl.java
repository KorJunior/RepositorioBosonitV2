package com.example.block7estadventa.application.implementation;

import com.example.block7estadventa.cache.CacheMethods;
import com.example.block7estadventa.application.HistoricoVentaService;
import com.example.block7estadventa.controller.dto.historico.HistoricoVentasOutPut;
import com.example.block7estadventa.domain.Cliente;
import com.example.block7estadventa.domain.HistoricoVenta;
import com.example.block7estadventa.domain.Producto;
import com.example.block7estadventa.repository.ClienteRepository;
import com.example.block7estadventa.repository.HistoricoVentaRepository;
import com.example.block7estadventa.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class HistoricoVentaServiceImpl implements HistoricoVentaService {

    @Autowired
    private HistoricoVentaRepository historicoVentaRepository;
    @Autowired
    private CacheMethods cacheMethods;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProductoRepository productoRepository;



    @Transactional
    @Override
    public List<HistoricoVentasOutPut> obtenerHistoricoVentaMesConAno(int mes, int ano) {
        List<HistoricoVenta> historicosGuardados = new ArrayList<>();
        List<Map<String, Object>> result;
        Optional<List<HistoricoVenta>> historicoVentaOptional;

        historicoVentaOptional = historicoVentaRepository.findByMesAndAno(mes, ano);

        if (historicoVentaOptional.isPresent()) {
            if (!historicoVentaOptional.get().isEmpty()) {
                System.out.println(historicoVentaOptional.get());
                return convertirAListaHistoricoVentasOutPut(historicoVentaOptional.get());
            }

        }
        result = obtenerFacturasMesAno(mes, ano);
        for (Map<String, Object> map : result) {
            guardarHistorico(ano, map, mes, historicosGuardados);
        }
        return convertirAListaHistoricoVentasOutPut(historicosGuardados);
    }


    private List<Map<String, Object>> obtenerFacturasMesAno(int mes, int ano) {
        String url = "http://localhost:8080/cabeceradefacturas/getFacturaByMonthAndYear?mes=" + mes + "&ano=" + ano;
        WebClient webClient = WebClient.create();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Map<String, Object>>>() {
                })
                .block();
    }

    public List<HistoricoVentasOutPut> obtenerHistoricoVentaAno(int ano) {
        List<Map<String, Object>> result = obtenerFacturaAno(ano);
        List<HistoricoVenta> historicosGuardados = new ArrayList<>();

        for (Map<String, Object> map : result) {
            Object fechaObj = map.get("fecha");
            if (fechaObj instanceof String) {
                ZonedDateTime fecha = ZonedDateTime.parse((String) fechaObj, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                int mes = fecha.getMonthValue();

                Optional<List<HistoricoVenta>> historicoVentaOptional = historicoVentaRepository.findByMesAndAno(mes, ano);

                if (historicoVentaOptional.isPresent() && !historicoVentaOptional.get().isEmpty()) {
                    return convertirAListaHistoricoVentasOutPut(historicoVentaOptional.get());
                } else {
                    guardarHistorico(ano, map, mes, historicosGuardados);
                }
            }
        }

        return convertirAListaHistoricoVentasOutPut(historicosGuardados);
    }

    List<HistoricoVentasOutPut> convertirAListaHistoricoVentasOutPut(List<HistoricoVenta> historicoVentas) {
        List<HistoricoVentasOutPut> historicoVentasOutPuts = new ArrayList<>();
        HistoricoVentasOutPut historicoVentasOutPut;
        for (HistoricoVenta historicoVenta : historicoVentas) {
            historicoVentasOutPut = new HistoricoVentasOutPut(historicoVenta);
            historicoVentasOutPut.setProducto(convertirIdProductoEnNombreInternamente(historicoVenta.getIdProducto()));
            historicoVentasOutPut.setNombreCliente(convertirDniClienteEnNombreInternamente(historicoVenta.getIdCliente()));
            historicoVentasOutPuts.add(historicoVentasOutPut);
        }
        return historicoVentasOutPuts;

    }
    String convertirIdProductoEnNombreInternamente(Long idProducto) {
        Optional<Producto>producto=productoRepository.findById(idProducto.intValue());

        if (producto.isPresent()){
            Producto producto1=producto.get();
            return producto1.getNombre();
        }else{
            return "Producto no encontrado";
        }
    }
    String convertirDniClienteEnNombreInternamente(String idCliente) {
        Optional<Cliente>cliente=clienteRepository.findById(idCliente);

        if (cliente.isPresent()){
            Cliente cliente1=cliente.get();
            return cliente1.getNombre();
        }else{
            return "Cliente no encontrado";
        }

    }





    private void guardarHistorico(int ano, Map<String, Object> map, int mes, List<HistoricoVenta> historicosGuardados) {

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> productos = (List<Map<String, Object>>) map.get("productos");

        for (Map<String, Object> producto : productos) {
            HistoricoVenta historicoVenta = new HistoricoVenta();
            historicoVenta.setMes(mes);
            historicoVenta.setAno(ano);
            historicoVenta.setIdCliente((String) map.get("cliente"));


            Object productoId = producto.get("producto");
            Object cantidad = producto.get("cantidad");
            Object idLinea = producto.get("idLineaDeFactura");

            if (productoId instanceof Number) {
                historicoVenta.setIdProducto(((Number) productoId).longValue());
            }

            if (cantidad instanceof Number) {
                historicoVenta.setCantidad(((Number) cantidad).intValue());
            }
            if (idLinea instanceof Number) {
               int id= ((Number) idLinea).intValue();
               historicoVenta.setImporteTotal(obtenerImporteTotal(id));
            }
            historicoVentaRepository.save(historicoVenta);
            historicosGuardados.add(historicoVenta);
        }
    }

    private float obtenerImporteTotal(int id) {
        String url = "http://localhost:8080/lineasdefactura/" + id;
        WebClient webClient = WebClient.create();

        Map<String, Object> response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .block();

        assert response != null;
        Number importe = (Number) response.get("importe");
        return importe.floatValue();
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