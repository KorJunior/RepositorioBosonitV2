package com.example.block7jpaconrelacionesyllamadasentremicros.application.implementation;

import com.example.block7jpaconrelacionesyllamadasentremicros.application.LineasDeFacturaService;
import com.example.block7jpaconrelacionesyllamadasentremicros.domain.CabeceraDeFactura;
import com.example.block7jpaconrelacionesyllamadasentremicros.domain.LineasDeFactura;
import com.example.block7jpaconrelacionesyllamadasentremicros.repository.CabeceraDeFacturaRepository;
import com.example.block7jpaconrelacionesyllamadasentremicros.repository.LineasDeFacturaRepository;
import com.example.block7jpaconrelacionesyllamadasentremicros.repository.ProductoRepository;
import org.example.dto.dtoLineasDeFactura.lineasDeFacturaInputDto.LineasDeFacturaInputDto;
import org.example.dto.dtoLineasDeFactura.lineasDeFacturaOutPut.LineasDeFacturaOutPutComplete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LineasDeFacturaServiceImpl implements LineasDeFacturaService {
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private CabeceraDeFacturaRepository cabeceraDeFacturaRepository;
    @Autowired
    private LineasDeFacturaRepository lineasDeFacturaRepository;
    @Override
    public LineasDeFacturaOutPutComplete getLineasDeFacutra(int id) {
        return lineasDeFacturaRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe la linea de factura")).toLineasDeFacturaOutPutComplete();
    }

    @Override
    public LineasDeFacturaOutPutComplete addLineasDeFacutura(LineasDeFacturaInputDto lineasDeFacturaInputDto) {
        LineasDeFactura lineasDeFactura = new LineasDeFactura(lineasDeFacturaInputDto);
        Optional<CabeceraDeFactura> optionalCabeceraDeFactura = cabeceraDeFacturaRepository.findById(lineasDeFacturaInputDto.getIdFactura());
        productoRepository.findById(lineasDeFacturaInputDto.getIdProducto()).ifPresent(lineasDeFactura::setProducto);
        if (lineasDeFactura.getPrecio()==0.0){
            lineasDeFactura.setPrecio(lineasDeFactura.getProducto().getPrecioProducto());
        }
        lineasDeFactura.setImporte(lineasDeFactura.getCantidad()*lineasDeFactura.getPrecio());


        if (optionalCabeceraDeFactura.isPresent()) {

            CabeceraDeFactura cabeceraDeFactura = optionalCabeceraDeFactura.get();
            List<LineasDeFactura> lineasDeFacturas = cabeceraDeFactura.getLineasDeFactura();

            float importeTotal=cabeceraDeFactura.getImporteTotal()+lineasDeFactura.getImporte();
            for (LineasDeFactura lineasDeFactura2: lineasDeFacturas) {
                importeTotal=importeTotal+lineasDeFactura2.getImporte();
            }
            cabeceraDeFactura.setImporteTotal(importeTotal);
            lineasDeFactura.setCabeceraDeFactura(cabeceraDeFactura);
            cabeceraDeFacturaRepository.save(cabeceraDeFactura);
        }





        lineasDeFacturaRepository.save(lineasDeFactura);
        return lineasDeFactura.toLineasDeFacturaOutPutComplete();
    }

    @Override
    public LineasDeFacturaOutPutComplete updateLineasFacutra(LineasDeFacturaInputDto lineasDeFacturaInputDto) {
        return null;
    }

    @Override
    public void deleteLineasFactura(int id) {

    }

    @Override
    public List<LineasDeFacturaOutPutComplete> getAllLineasDeFactura() {
        return lineasDeFacturaRepository.findAll().stream()
                .map(LineasDeFactura::toLineasDeFacturaOutPutComplete)
                .collect(Collectors.toList());
    }
}
