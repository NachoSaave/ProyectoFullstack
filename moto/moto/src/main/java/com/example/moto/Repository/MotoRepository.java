package com.example.moto.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.moto.Model.Moto;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Long> {   
}