package com.example.motocicleta.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.motocicleta.Model.Moto;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Long> {   
}