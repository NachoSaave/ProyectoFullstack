package com.example.Pago.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Pago.Model.Pago;

public interface pagoRepository extends JpaRepository<Pago, Long> {
}