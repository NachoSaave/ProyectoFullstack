package com.example.envio.Model.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TransporteDTO {
    private Long id;
    private String nombreConductor;
    private String empresa;
    private String tipoTransporte;
}
