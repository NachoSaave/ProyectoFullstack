package com.example.Pago.Model.DTO;

import lombok.Data;

@Data
public class PagoResponseDTO {
    private Long id;
    private Long saleId;
    private Double monto;
    private String metodoPago;
    private String estado;

    private ClienteDTO cliente;
}
