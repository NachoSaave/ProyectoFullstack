package com.example.factura.Model.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ClienteDTO {

    private Long id;
    private String nombre;
    private String telefono;
}
