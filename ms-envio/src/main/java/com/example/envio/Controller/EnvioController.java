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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/envios")
public class EnvioController {
    @Autowired
    private EnvioService serv;

    @GetMapping
    public List<Envio> getAll() {   
        log.info("INFORMACION: Se solicita lista de envios");
        return serv.findAll();
    }   

    @GetMapping("/{id}")
    public Map<String, Object> obtenerEnvioDetallado(@PathVariable Long id) {
        log.info("INFORMACION: Se solicita envio detallado por id {}", id);
        return serv.obtenerEnvioConDetalles(id);
    }

    @PostMapping
    public Envio guardar(@RequestBody Envio envio) {
        log.info("INFORMACION: Se crea envio con los siguientes datos: {}", envio.toString());
        return serv.save(envio);
    }

    @PutMapping("/{id}")
    public Envio actualizar(@PathVariable Long id, @RequestBody Envio envio) {
        log.info("INFORMACION: Se actualiza envio de id {} con los siguientes datos: {}", id, envio.toString());
        envio.setId(id);
        return serv.update(envio);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        log.info("INFORMACION: Se elimina envio de id {}", id);
        serv.deleteById(id);
    }
}