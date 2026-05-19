package com.example.Pago.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "pagos")
@Getter
@Setter
public class Pago {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long saleId;

    private Double monto;

    private String metodoPago;

    private String estado;

    private Long clienteId;
    private String clienteNombre;
    private String clienteTelefono;
    
}
