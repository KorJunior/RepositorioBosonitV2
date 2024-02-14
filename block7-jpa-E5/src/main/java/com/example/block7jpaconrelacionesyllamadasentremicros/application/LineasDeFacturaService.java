package com.example.block7jpaconrelacionesyllamadasentremicros.application;


import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoLineasDeFactura.lineasDeFacturaInputDto.LineasDeFacturaInputDto;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoLineasDeFactura.lineasDeFacturaOutPut.LineasDeFacturaOutPutComplete;

import java.util.List;

public interface LineasDeFacturaService {
    LineasDeFacturaOutPutComplete getLineasDeFacutra(int id);
    LineasDeFacturaOutPutComplete addLineasDeFacutura(LineasDeFacturaInputDto lineasDeFacturaInputDto);
    LineasDeFacturaOutPutComplete updateLineasFacutra(LineasDeFacturaInputDto lineasDeFacturaInputDto);
    void deleteLineasFactura(int id);
    List<LineasDeFacturaOutPutComplete> getAllLineasDeFactura();
}
