package com.example.venta.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.venta.Model.Dto.VentaSolicitudDTO;
import com.example.venta.Model.venta;
import com.example.venta.Service.ventaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/ventas")
@RequiredArgsConstructor
public class ventaController {

    private final ventaService service;

    @PostMapping
    public ResponseEntity<?> crearVenta(@RequestBody VentaSolicitudDTO dto) {
        log.info("INFORMACION: Creando venta con datos: {}", dto);
        try {
            venta nueva = service.crearVenta(dto);
            return ResponseEntity.ok(nueva);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<venta>> listar() {
        log.info("INFORMACION: Obteniendo lista de ventas");
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        log.info("INFORMACION: Buscando venta con ID: {}", id);

        venta v = service.buscarPorId(id);

        if (v == null) {
            return ResponseEntity.badRequest().body("No encontrada");
        }

        return ResponseEntity.ok(v);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        log.info("INFORMACION: Eliminando venta con ID: {}", id);
        service.eliminar(id);
        return ResponseEntity.ok("Eliminada");
    }
}