package com.example.destino.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.destino.Model.Destino;

@Repository
public interface DestinoRepository extends JpaRepository<Destino, Long>{

    public List<Destino> findByComuna(String comuna);
    public List<Destino> findByCiudad(String ciudad);
}