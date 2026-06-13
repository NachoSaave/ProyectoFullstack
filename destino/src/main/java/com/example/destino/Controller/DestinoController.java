package com.example.destino.Controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.destino.Model.Destino;
import com.example.destino.Service.DestinoService;

@Slf4j
@RestController
@RequestMapping("/api/v1/destino")
public class DestinoController {

    @Autowired
    private DestinoService serv;

    @GetMapping("/{id}")
    public Destino getDestinoById(@PathVariable Long id) {
        log.info("INFORMACION: Se solicita destino por id: {}", id);
        return serv.getDestinoById(id);
    }

    @PostMapping
    public Destino saveDestino(@RequestBody Destino destino) {
        log.info("INFORMACION: Se crea destino con los siguientes datos: {}", destino.toString());
        return serv.saveDestino(destino);
    }

    @PutMapping("/{id}")
    public Destino updateDestino(@PathVariable Long id, @RequestBody Destino destino) {
    log.info("INFORMACION: Se actualiza destino de id: {} con los siguientes datos: {}", id, destino.toString());
    destino.setId(id);
    return serv.updateDestino(destino);
}

    @DeleteMapping("/{id}")
    public void deleteDestino(@PathVariable Long id) {
        log.info("INFORMACION: Se elimina destino de id: {}", id);
        serv.deleteDestino(id);
    }

    @GetMapping("/comuna/{comuna}")
    public List<Destino> getDestinoByComuna(@PathVariable String comuna) {
        log.info("INFORMACION: Se solicita destino por comuna: {}", comuna);
        return serv.getDestinoByComuna(comuna);
    }

    @GetMapping("/ciudad/{ciudad}")
    public List<Destino> getDestinoByCiudad(@PathVariable String ciudad) {
        log.info("INFORMACION: Se solicita destino por ciudad: {}", ciudad);
        return serv.getDestinoByCiudad(ciudad);
    }
}