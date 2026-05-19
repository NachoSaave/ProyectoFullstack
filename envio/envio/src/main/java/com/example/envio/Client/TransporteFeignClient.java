package com.example.envio.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.envio.Model.DTO.TransporteDTO;

@FeignClient(
        name = "Transporte",
        url = "http://localhost:8088"
)
public interface TransporteFeignClient {

    @GetMapping("/api/v1/transportes/{id}")
    TransporteDTO getTransporteById(@PathVariable("id") Long id);
}
