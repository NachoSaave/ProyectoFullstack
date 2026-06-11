package com.example.Pago.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Pago.Model.DTO.MotoDTO;

@FeignClient(
        name = "Moto",
        url = "http://localhost:8082"
)
public interface MotoFeingClient {
    @GetMapping("/api/v1/motos/{id}")
    MotoDTO getMotoById(@PathVariable("id") Long id);
}
