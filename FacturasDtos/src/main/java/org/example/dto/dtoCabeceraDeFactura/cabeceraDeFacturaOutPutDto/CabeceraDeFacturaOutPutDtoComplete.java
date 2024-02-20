package org.example.dto.dtoCabeceraDeFactura.cabeceraDeFacturaOutPutDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.dtoCliente.clienteOutput.ClienteOutPutDtoSimple;
import org.example.dto.dtoLineasDeFactura.lineasDeFacturaOutPut.LineasDeFacturaOutPutSimple;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CabeceraDeFacturaOutPutDtoComplete {

    private int idFactura;
    private Date fechaFactura;
    private float importeTotal;
    private ClienteOutPutDtoSimple cliente;
    private List<LineasDeFacturaOutPutSimple> lineasFactura;


    public CabeceraDeFacturaOutPutDtoComplete(int idFactura, Date fechaFactura, float importeTotal, ClienteOutPutDtoSimple clienteOutputDtoProvincia) {
        this.idFactura = idFactura;
        this.fechaFactura = fechaFactura;
        this.importeTotal = importeTotal;
        this.cliente = clienteOutputDtoProvincia;
    }
}
