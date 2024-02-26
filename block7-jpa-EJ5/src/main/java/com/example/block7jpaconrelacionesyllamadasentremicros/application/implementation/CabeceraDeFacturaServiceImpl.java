package com.example.block7jpaconrelacionesyllamadasentremicros.application.implementation;

import com.example.block7jpaconrelacionesyllamadasentremicros.application.CabeceraDeFacturaService;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCabeceraDeFactura.cabeceraDeFacturaInput.CabeceraDeFacturaInputDto;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCabeceraDeFactura.cabeceraDeFacturaInput.FacturaInputDto;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCabeceraDeFactura.cabeceraDeFacturaOutPutDto.CabeceraDeFacturaOutPutDtoComplete;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCabeceraDeFactura.cabeceraDeFacturaOutPutDto.FacturaOutPutHistorico;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCabeceraDeFactura.cabeceraDeFacturaOutPutDto.FacturaOutput;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoLineasDeFactura.lineasDeFacturaInputDto.LineasDeFacturaInputFactura;
import com.example.block7jpaconrelacionesyllamadasentremicros.domain.CabeceraDeFactura;
import com.example.block7jpaconrelacionesyllamadasentremicros.domain.Cliente;
import com.example.block7jpaconrelacionesyllamadasentremicros.domain.LineasDeFactura;
import com.example.block7jpaconrelacionesyllamadasentremicros.domain.Producto;
import com.example.block7jpaconrelacionesyllamadasentremicros.repository.CabeceraDeFacturaRepository;
import com.example.block7jpaconrelacionesyllamadasentremicros.repository.ClienteRepository;
import com.example.block7jpaconrelacionesyllamadasentremicros.repository.LineasDeFacturaRepository;
import com.example.block7jpaconrelacionesyllamadasentremicros.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CabeceraDeFacturaServiceImpl implements CabeceraDeFacturaService {
    @Autowired
    private CabeceraDeFacturaRepository cabeceraDeFacturaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private LineasDeFacturaRepository lineasDeFacturaRepository;


    @Override
    public CabeceraDeFacturaOutPutDtoComplete getCabeceraDeFactura(int id) {
        return null;
    }

    @Override
    public CabeceraDeFacturaOutPutDtoComplete addCabeceraDeFactura(CabeceraDeFacturaInputDto cabeceraDeFacturaInputDto) {
        CabeceraDeFactura cabeceraDeFactura = new CabeceraDeFactura(cabeceraDeFacturaInputDto);
        clienteRepository.findById(cabeceraDeFacturaInputDto.getDni()).ifPresent(cabeceraDeFactura::setCliente);
        cabeceraDeFacturaRepository.save(cabeceraDeFactura);

        return cabeceraDeFactura.toCabeceraDeFacturaOutPutDtoComplete();
    }

    @Override
    public CabeceraDeFacturaOutPutDtoComplete updateCabeceraDeFactura(CabeceraDeFacturaInputDto cabeceraDeFacturaInputDto) {
        return null;
    }

    @Override
    public void deleteCabeceraDeFactura(int id) {

    }

    @Override
    public List<CabeceraDeFacturaOutPutDtoComplete> getAllCabeceraDeFacturas() {
        return converirCabeceraDeFacturaListToCabeceraDeFacturaOutPutDtoCompleteList();
    }

    private List<CabeceraDeFacturaOutPutDtoComplete> converirCabeceraDeFacturaListToCabeceraDeFacturaOutPutDtoCompleteList() {
        return cabeceraDeFacturaRepository.findAll().stream()
                .map(CabeceraDeFactura::toCabeceraDeFacturaOutPutDtoComplete)
                .collect(Collectors.toList());
    }
    @Transactional
    public FacturaOutput addFactura(FacturaInputDto factura) {
        CabeceraDeFactura facturaBase = new CabeceraDeFactura(factura);
        List<LineasDeFacturaInputFactura> lineasDeFacturas = factura.getLineasDeFactura();
        List<LineasDeFactura> listaLineasDeFactura;
        System.out.println("Hola 1");

        Cliente cliente = clienteRepository.findById(factura.getDni()).orElse(null);
        if (cliente == null) {
            return null;
        }
        facturaBase.setCliente(cliente);


        if (lineasDeFacturas != null){
            listaLineasDeFactura =convertirListaLinesDeFactura(lineasDeFacturas);
            if (listaLineasDeFactura.isEmpty()){
                return null;
            }

            facturaBase.setLineasDeFactura(listaLineasDeFactura);
            float a=calcularImporteTotal(listaLineasDeFactura);
            System.out.println("Hola 2");
            facturaBase.setImporteTotal(a);
            cabeceraDeFacturaRepository.save(facturaBase);
            asignarFacturaALasLineas(listaLineasDeFactura, facturaBase);
            lineasDeFacturaRepository.saveAll(listaLineasDeFactura);


            return new FacturaOutput(facturaBase);
        }else{
            System.out.println("No hay lineas de factura");
        }


        return null;
    }

    private void asignarFacturaALasLineas(List<LineasDeFactura> listaLineasDeFactura, CabeceraDeFactura facturaBase) {
        for (LineasDeFactura lineasDeFactura : listaLineasDeFactura) {
            lineasDeFactura.setCabeceraDeFactura(facturaBase);
        }
    }

    private float calcularImporteTotal(List<LineasDeFactura> listaLineasDeFactura) {
        float importeTotal=0;
        for (LineasDeFactura lineasDeFactura : listaLineasDeFactura) {
            importeTotal+=lineasDeFactura.getImporte();
        }
        return importeTotal;
    }

    private List<LineasDeFactura> convertirListaLinesDeFactura(List<LineasDeFacturaInputFactura> lineasDeFacturas) {
        List<LineasDeFactura> listaLineasDevolver= new ArrayList<>();

        for (LineasDeFacturaInputFactura lineasDeFactura : lineasDeFacturas) {
            LineasDeFactura lineasDeFactura1 = new LineasDeFactura(lineasDeFactura);
            Optional<Producto> producto =productoRepository.findById((long) lineasDeFactura.getIdProducto());

            if (producto.isEmpty()) {
                System.out.println("El producto no existe");
                return null;
            }
                lineasDeFactura1.setProducto(producto.get());
                lineasDeFactura1.setCantidad(lineasDeFactura.getCantidad());
                if (lineasDeFactura.getPrecio() != 0){
                    lineasDeFactura1.setPrecio(lineasDeFactura.getPrecio());
                    lineasDeFactura1.setImporte(lineasDeFactura.getPrecio() * lineasDeFactura.getCantidad());
                }else{
                    lineasDeFactura1.setPrecio(producto.get().getPrecioProducto());
                    lineasDeFactura1.setImporte(producto.get().getPrecioProducto() * lineasDeFactura.getCantidad());
                }
                listaLineasDevolver.add(lineasDeFactura1);


        }
        return listaLineasDevolver;
    }

    public List<FacturaOutput> getAllFacturas() {
        return convertirListaFacturas(cabeceraDeFacturaRepository.findAll());
    }

    @Override
    public List<FacturaOutput> findByFacturaWithClienteAndRangoDeFechas(String idCliente, String fechaInicio, String fechaFin) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaInicioDate = null;
        Date fechaFinDate = null;
        try {
            fechaInicioDate = formatter.parse(fechaInicio);
            fechaFinDate = formatter.parse(fechaFin);
        } catch (ParseException e) {
             throw new RuntimeException("Error en el formato de la fecha");
        }
        Optional<List<CabeceraDeFactura>> factura = cabeceraDeFacturaRepository.findByFacturaWithClienteAndRangoDeFechas(idCliente, fechaInicioDate, fechaFinDate);

        return factura.orElseThrow(() -> new RuntimeException("No se ha encontrado la factura"))
                .stream()
                .map(FacturaOutput::new)
                .collect(Collectors.toList());
    }

    @Override
    public FacturaOutput findByIdCabecera(Integer id) {
        Optional<CabeceraDeFactura> factura = cabeceraDeFacturaRepository.findByIdCabecera(id);
        return factura.map(FacturaOutput::new).orElseThrow(() -> new RuntimeException("No se ha encontrado la factura"));
    }

    @Override
    public List<FacturaOutPutHistorico> findByYear(int ano) {
        return  cabeceraDeFacturaRepository.findByYear(ano)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado la factura"))
                .stream()
                .map(FacturaOutPutHistorico::new)
                .toList();

    }

    @Override
    public List<FacturaOutPutHistorico> findByMonthAndYear(int mes, int ano) {
        return cabeceraDeFacturaRepository.findByMonthAndYear(mes, ano)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado la factura"))
                .stream()
                .map(FacturaOutPutHistorico::new)
                .toList();
    }


    private List<FacturaOutput> convertirListaFacturas(List<CabeceraDeFactura> all) {
        List<FacturaOutput> listaFacturas = new ArrayList<>();
        for (CabeceraDeFactura cabeceraDeFactura : all) {
            listaFacturas.add(new FacturaOutput(cabeceraDeFactura));
        }
        return listaFacturas;
    }

    public List<FacturaOutput> getFacturaByCodigoProducto(Long codigo) {
        Optional<List<CabeceraDeFactura>>  factura = cabeceraDeFacturaRepository.findByCodigoDeProducto(codigo);

        return factura.orElseThrow(()-> new RuntimeException("No se ha encontrado la factura")).
                stream().map(FacturaOutput::new).collect(Collectors.toList());

    }
}
