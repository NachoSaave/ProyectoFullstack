package com.example.Pago.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.example.Pago.Model.Pago;
import com.example.Pago.Service.pagoService;

@Slf4j
@RestController
@RequestMapping("/api/v1/pagos")
@RequiredArgsConstructor
public class pagoController {
    @Autowired
    private final pagoService serv;

    @GetMapping
    public List<Pago> getAll() {
        log.info("INFORMACION: Obteniendo lista de pagos");
        return serv.findAll();
    }   

    @GetMapping("/{id}")
    public Map<String, Object> obtenerEnvioDetallado(@PathVariable Long id) {
        log.info("INFORMACION: Obteniendo detalles del pago con ID: {}", id);
        return serv.obtenerPagoConDetalles(id);
    }

    @PostMapping
    public Pago guardar(@RequestBody Pago pago) {
        log.info("INFORMACION: Guardando nuevo pago con datos: {}", pago.toString());
        return serv.save(pago);
    }

    @PutMapping("/{id}")
    public Pago actualizar(@PathVariable Long id, @RequestBody Pago pago) {
        log.info("INFORMACION: Actualizando pago con ID: {} con datos: {}", id, pago.toString());
        return serv.update(pago);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        log.info("INFORMACION: Eliminando pago con ID: {}", id);
        serv.deleteById(id);
    }
}