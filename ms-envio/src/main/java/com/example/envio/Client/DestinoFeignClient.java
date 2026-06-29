package com.example.envio.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.envio.Model.DTO.DestinoDTO;

@FeignClient(
        name = "Destino",
        url = "http://localhost:8089"
)
public interface DestinoFeignClient {

    @GetMapping("/api/v1/destino/{id}")
    DestinoDTO getDestinoById(@PathVariable("id") Long id);
}
