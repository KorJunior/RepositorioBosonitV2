package com.example.block7jpaconrelacionesyllamadasentremicros.application;

import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCabeceraDeFactura.cabeceraDeFacturaInput.CabeceraDeFacturaInputDto;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCabeceraDeFactura.cabeceraDeFacturaInput.FacturaInputDto;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCabeceraDeFactura.cabeceraDeFacturaOutPutDto.CabeceraDeFacturaOutPutDtoComplete;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCabeceraDeFactura.cabeceraDeFacturaOutPutDto.FacturaOutput;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

public interface CabeceraDeFacturaService {

    CabeceraDeFacturaOutPutDtoComplete getCabeceraDeFactura(int id);
    CabeceraDeFacturaOutPutDtoComplete addCabeceraDeFactura(CabeceraDeFacturaInputDto cabeceraDeFacturaInputDto);
    CabeceraDeFacturaOutPutDtoComplete updateCabeceraDeFactura(CabeceraDeFacturaInputDto cabeceraDeFacturaInputDto);
    void deleteCabeceraDeFactura(int id);
    List<CabeceraDeFacturaOutPutDtoComplete> getAllCabeceraDeFacturas();
    List<FacturaOutput> getFacturaByCodigoProducto(Long codigo);
    FacturaOutput addFactura(FacturaInputDto factura);
    List<FacturaOutput> getAllFacturas();
    List<FacturaOutput> findByFacturaWithClienteAndRangoDeFechas(String idCliente, String fechaInicio, String fechaFin);
    FacturaOutput findByIdCabecera(Integer id);

}
