package com.example.Pago.Model.DTO;

import lombok.Data;

@Data

public class MotoDTO {
    private Long id;
    
    private String marca;
    private String modelo;
    private Double precio;
    private int ano;
    private String cc;
}
