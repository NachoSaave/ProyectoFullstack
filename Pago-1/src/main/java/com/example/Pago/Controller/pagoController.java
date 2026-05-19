package com.example.Pago.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.example.Pago.Model.Pago;
import com.example.Pago.Model.DTO.PagoResponseDTO;
import com.example.Pago.Service.pagoService;

@RestController
@RequestMapping("/api/v1/pagos")
@RequiredArgsConstructor
public class pagoController {

    private final pagoService service;

    // CREAR PAGO
    @PostMapping
    public ResponseEntity<PagoResponseDTO> save(@RequestBody Pago pago) {
        return ResponseEntity.ok(service.proceso(pago));
    }

    // LISTAR
    @GetMapping
    public ResponseEntity<List<PagoResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    //  BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {

        PagoResponseDTO pago = service.buscarPorId(id);

        if (pago == null) {
            return ResponseEntity.badRequest().body("No encontrado");
        }

        return ResponseEntity.ok(pago);
    }
}