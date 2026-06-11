package com.example.envio.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.envio.Model.Envio;
import com.example.envio.Service.EnvioService;

@RestController
@RequestMapping("/api/v1/envios")
public class EnvioController {
    @Autowired
    private EnvioService serv;

    @GetMapping
    public List<Envio> getAll() {
        return serv.findAll();
    }   

    @GetMapping("/{id}")
    public Map<String, Object> obtenerEnvioDetallado(@PathVariable Long id) {
        return serv.obtenerEnvioConDetalles(id);
    }

    @PostMapping
    public Envio guardar(@RequestBody Envio envio) {
        return serv.save(envio);
    }

    @PutMapping("/{id}")
    public Envio actualizar(@PathVariable Long id, @RequestBody Envio envio) {
        envio.setId(id);
        return serv.update(envio);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        serv.deleteById(id);
    }
}