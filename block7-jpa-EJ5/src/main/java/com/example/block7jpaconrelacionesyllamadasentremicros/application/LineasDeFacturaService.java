package com.example.block7jpaconrelacionesyllamadasentremicros.application;




import org.example.dto.dtoLineasDeFactura.lineasDeFacturaInputDto.LineasDeFacturaInputDto;
import org.example.dto.dtoLineasDeFactura.lineasDeFacturaOutPut.LineasDeFacturaOutPutComplete;

import java.util.List;

public interface LineasDeFacturaService {
    LineasDeFacturaOutPutComplete getLineasDeFacutra(int id);
    LineasDeFacturaOutPutComplete addLineasDeFacutura(LineasDeFacturaInputDto lineasDeFacturaInputDto);
    LineasDeFacturaOutPutComplete updateLineasFacutra(LineasDeFacturaInputDto lineasDeFacturaInputDto);
    void deleteLineasFactura(int id);
    List<LineasDeFacturaOutPutComplete> getAllLineasDeFactura();
}
