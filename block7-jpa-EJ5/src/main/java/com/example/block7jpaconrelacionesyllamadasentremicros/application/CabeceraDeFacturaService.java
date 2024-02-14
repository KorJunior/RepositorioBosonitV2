package com.example.block7jpaconrelacionesyllamadasentremicros.application;

import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCabeceraDeFactura.cabeceraDeFacturaInput.CabeceraDeFacturaInputDto;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCabeceraDeFactura.cabeceraDeFacturaOutPutDto.CabeceraDeFacturaOutPutDtoComplete;

import java.util.List;

public interface CabeceraDeFacturaService {

    CabeceraDeFacturaOutPutDtoComplete getCabeceraDeFactura(int id);
    CabeceraDeFacturaOutPutDtoComplete addCabeceraDeFactura(CabeceraDeFacturaInputDto cabeceraDeFacturaInputDto);
    CabeceraDeFacturaOutPutDtoComplete updateCabeceraDeFactura(CabeceraDeFacturaInputDto cabeceraDeFacturaInputDto);
    void deleteCabeceraDeFactura(int id);
    List<CabeceraDeFacturaOutPutDtoComplete> getAllCabeceraDeFacturas();

}
