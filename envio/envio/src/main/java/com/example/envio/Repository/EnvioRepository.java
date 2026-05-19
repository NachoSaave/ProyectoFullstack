package com.example.envio.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.envio.Model.Envio;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Long>{
}