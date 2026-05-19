package com.example.factura.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.factura.Model.DTO.ClienteDTO;

@FeignClient(name = "cliente", url = "http://localhost:8081")
public interface ClienteFeignClient {

    @GetMapping("/api/v1/clientes/{id}")
    ClienteDTO getClienteById(@PathVariable("id") Long id);

}