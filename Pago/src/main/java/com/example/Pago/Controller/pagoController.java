package com.example.Pago.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.example.Pago.Model.Pago;
import com.example.Pago.Service.pagoService;

@RestController
@RequestMapping("/api/v1/pagos")
@RequiredArgsConstructor
public class pagoController {
    @Autowired
    private final pagoService serv;

    @GetMapping
    public List<Pago> getAll() {
        return serv.findAll();
    }   

    @GetMapping("/{id}")
    public Map<String, Object> obtenerEnvioDetallado(@PathVariable Long id) {
        return serv.obtenerPagoConDetalles(id);
    }

    @PostMapping
    public Pago guardar(@RequestBody Pago pago) {
        return serv.save(pago);
    }

    @PutMapping("/{id}")
    public Pago actualizar(@PathVariable Long id, @RequestBody Pago pago) {
        pago.setId(id);
        return serv.update(pago);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        serv.deleteById(id);
    }
}