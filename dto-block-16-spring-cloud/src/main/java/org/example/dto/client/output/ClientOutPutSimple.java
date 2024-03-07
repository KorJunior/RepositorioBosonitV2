package org.example.dto.client.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientOutPutSimple {
    private String nombre;
    private String apellido;
    private int edad;
    private String email;
    private int telefono;

}
