package com.example.transporte.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.transporte.Model.Transporte;

@Repository
public interface TransporteRepository extends JpaRepository<Transporte, Long>{

    List<Transporte> findByEmpresa(String empresa);
}