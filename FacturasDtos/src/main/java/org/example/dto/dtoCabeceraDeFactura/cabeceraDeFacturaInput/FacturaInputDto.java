package org.example.dto.dtoCabeceraDeFactura.cabeceraDeFacturaInput;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.dtoLineasDeFactura.lineasDeFacturaInputDto.LineasDeFacturaInputFactura;

import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaInputDto {
    private Date fechaFactura;
    private String dni;
    private List<LineasDeFacturaInputFactura> lineasDeFactura;
}
