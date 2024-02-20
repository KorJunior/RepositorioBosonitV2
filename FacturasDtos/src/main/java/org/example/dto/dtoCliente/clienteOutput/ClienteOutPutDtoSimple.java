package org.example.dto.dtoCliente.clienteOutput;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.dtoProvincia.provinciaOutPut.ProvinciaOutPutSimple;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteOutPutDtoSimple {
    private String nombre;
    private ProvinciaOutPutSimple provincia;

//    public ClienteOutPutDtoSimple(Cliente cliente) {
//        this.nombre = cliente.getNombre();
//        this.provincia = cliente.getProvincia().toProvinciaOutPutSimple();
//    }

}
