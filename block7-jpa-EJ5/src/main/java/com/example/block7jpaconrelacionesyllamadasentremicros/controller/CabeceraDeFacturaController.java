package com.example.block7jpaconrelacionesyllamadasentremicros.controller;

import com.example.block7jpaconrelacionesyllamadasentremicros.application.CabeceraDeFacturaService;
import org.example.dto.dtoCabeceraDeFactura.cabeceraDeFacturaInput.CabeceraDeFacturaInputDto;
import org.example.dto.dtoCabeceraDeFactura.cabeceraDeFacturaInput.FacturaInputDto;
import org.example.dto.dtoCabeceraDeFactura.cabeceraDeFacturaOutPutDto.CabeceraDeFacturaOutPutDtoComplete;
import org.example.dto.dtoCabeceraDeFactura.cabeceraDeFacturaOutPutDto.FacturaOutPutHistorico;
import org.example.dto.dtoCabeceraDeFactura.cabeceraDeFacturaOutPutDto.FacturaOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/cabeceradefacturas")
public class CabeceraDeFacturaController {

    @Autowired
    private CabeceraDeFacturaService cabeceraDeFacturaService;

    @PostMapping
    public CabeceraDeFacturaOutPutDtoComplete addCabeceraDeFactura(@RequestBody CabeceraDeFacturaInputDto cabeceraDeFacturaInputDto) {
        return cabeceraDeFacturaService.addCabeceraDeFactura(cabeceraDeFacturaInputDto);
    }
    @GetMapping
    public List<CabeceraDeFacturaOutPutDtoComplete> getAllCabeceraDeFacturas() {
        return cabeceraDeFacturaService.getAllCabeceraDeFacturas();
    }

    @PostMapping("/insertarFactura")
    public FacturaOutput addFactura(@RequestBody FacturaInputDto facturaInputDto) {
        System.out.println(facturaInputDto);
         return cabeceraDeFacturaService.addFactura(facturaInputDto);
    }
    @GetMapping("/getAllFacturas")
    public List<FacturaOutput> getAllFactura() {
        return cabeceraDeFacturaService.getAllFacturas();

    }
    @GetMapping("/getFacturaByCodigoProducto/{codigo}")
    public List<FacturaOutput> getFacturaByCodigoProducto(@PathVariable Long codigo) {
        return cabeceraDeFacturaService.getFacturaByCodigoProducto(codigo);
    }
    @GetMapping("/getFacturaByClienteAndRangoDeFechas")
    public List<FacturaOutput> getFacturaByClienteAndRangoDeFechas(@RequestParam String idCliente, @RequestParam String fechaInicio, @RequestParam String fechaFin) {
        return cabeceraDeFacturaService.findByFacturaWithClienteAndRangoDeFechas(idCliente, fechaInicio, fechaFin);
    }
    @GetMapping("/getFacturaById/{id}")
    public FacturaOutput getFacturaById(@PathVariable Integer id) {
        return cabeceraDeFacturaService.findByIdCabecera(id);
    }
    @GetMapping("/getFacturaByYear/{ano}")
    public List<FacturaOutPutHistorico> getFacturaByYear(@PathVariable int ano) {
        return cabeceraDeFacturaService.findByYear(ano);
    }
    @GetMapping("/getFacturaByMonthAndYear")
    public List<FacturaOutPutHistorico> getFacturaByMonthAndYear(@RequestParam int mes, @RequestParam int ano) {
        return cabeceraDeFacturaService.findByMonthAndYear(mes, ano);
    }


}
