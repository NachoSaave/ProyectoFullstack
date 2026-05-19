package com.example.envio.Model.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class DestinoDTO {
    private Long id;

    private String nombreCalle;
    private int numeroCalle;
    private String comuna;
    private String ciudad;
}
