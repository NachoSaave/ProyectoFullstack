package com.example.Pago.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Pago.Model.DTO.ClienteDTO;
@FeignClient(
        name = "Cliente"
)
public interface ClienteFeingClient {
    @GetMapping("/api/v1/clientes/{id}")
    ClienteDTO getClienteById(@PathVariable("id") Long id);

}
