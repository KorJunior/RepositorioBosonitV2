package com.example.block7jpaconrelacionesyllamadasentremicros.application.implementation;

import com.example.block7jpaconrelacionesyllamadasentremicros.application.CabeceraDeFacturaService;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCabeceraDeFactura.cabeceraDeFacturaInput.CabeceraDeFacturaInputDto;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCabeceraDeFactura.cabeceraDeFacturaInput.FacturaInputDto;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCabeceraDeFactura.cabeceraDeFacturaOutPutDto.CabeceraDeFacturaOutPutDtoComplete;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCabeceraDeFactura.cabeceraDeFacturaOutPutDto.FacturaOutput;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoLineasDeFactura.lineasDeFacturaInputDto.LineasDeFacturaInputFactura;
import com.example.block7jpaconrelacionesyllamadasentremicros.domain.CabeceraDeFactura;
import com.example.block7jpaconrelacionesyllamadasentremicros.domain.LineasDeFactura;
import com.example.block7jpaconrelacionesyllamadasentremicros.domain.Producto;
import com.example.block7jpaconrelacionesyllamadasentremicros.repository.CabeceraDeFacturaRepository;
import com.example.block7jpaconrelacionesyllamadasentremicros.repository.ClienteRepository;
import com.example.block7jpaconrelacionesyllamadasentremicros.repository.LineasDeFacturaRepository;
import com.example.block7jpaconrelacionesyllamadasentremicros.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public FacturaOutput addFactura(FacturaInputDto factura) {
        CabeceraDeFactura facturaBase = new CabeceraDeFactura(factura);
        List<LineasDeFacturaInputFactura> lineasDeFacturas = factura.getLineasDeFactura();
        List<LineasDeFactura> listaLineasDeFactura;
        System.out.println("Hola 1");

        clienteRepository.findById(factura.getDni()).ifPresent(facturaBase::setCliente);
        if (lineasDeFacturas != null){
            listaLineasDeFactura =convertirListaLinesDeFactura(lineasDeFacturas);
            facturaBase.setLineasDeFactura(listaLineasDeFactura);
            float a=calcularImporteTotal(listaLineasDeFactura);
            System.out.println("Hola 2");
            facturaBase.setImporteTotal(a);
            lineasDeFacturaRepository.saveAll(listaLineasDeFactura);
            cabeceraDeFacturaRepository.save(facturaBase);

            return new FacturaOutput(facturaBase);
        }else{
            System.out.println("No hay lineas de factura");
        }


        return null;
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
            if (producto.isPresent()) {
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
            }else{
                System.out.println("El producto no existe");
            }

        }


        return listaLineasDevolver;
    }

}
