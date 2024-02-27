package com.example.block7jpaconrelacionesyllamadasentremicros.application;



import org.example.dto.dtoCabeceraDeFactura.cabeceraDeFacturaInput.CabeceraDeFacturaInputDto;
import org.example.dto.dtoCabeceraDeFactura.cabeceraDeFacturaInput.FacturaInputDto;
import org.example.dto.dtoCabeceraDeFactura.cabeceraDeFacturaOutPutDto.CabeceraDeFacturaOutPutDtoComplete;
import org.example.dto.dtoCabeceraDeFactura.cabeceraDeFacturaOutPutDto.FacturaOutPutHistorico;
import org.example.dto.dtoCabeceraDeFactura.cabeceraDeFacturaOutPutDto.FacturaOutput;

import java.time.Year;
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

    List<FacturaOutPutHistorico> findByYear(int ano);
    List<FacturaOutPutHistorico> findByMonthAndYear(int mes, int ano);

}
