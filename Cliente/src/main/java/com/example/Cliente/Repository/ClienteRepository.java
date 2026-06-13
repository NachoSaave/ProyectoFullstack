package com.example.Cliente.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Cliente.Model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
