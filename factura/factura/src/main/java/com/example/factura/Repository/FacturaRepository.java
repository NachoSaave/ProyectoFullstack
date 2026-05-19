package com.example.factura.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.factura.Model.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Long>{
    List<Factura> findByidCliente(Long idCliente);
}