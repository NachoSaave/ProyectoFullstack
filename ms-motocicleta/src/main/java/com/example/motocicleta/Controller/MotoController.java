package com.example.moto.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.moto.Model.Moto;
import com.example.moto.Service.MotoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/motos")
public class MotoController {

    @Autowired
    private MotoService serv;

    @GetMapping
    public List<Moto> mostrarMotos(){
        log.info("INFORMACION: Obteniendo lista de motos");
        return serv.mostrar();
    }

    @PostMapping
    public Moto guardar(@RequestBody Moto moto){
        log.info("INFORMACION: Guardando nueva moto con marca: {}, modelo: {}", moto.toString());
        return serv.guardar(moto);
    }

    @GetMapping("/{id}") 
    public Moto buscarPorId(@PathVariable Long id){
        log.info("INFORMACION: Buscando moto por id: {}", id);
        return serv.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Moto actualizar(@PathVariable Long id, @RequestBody Moto moto){
        log.info("INFORMACION: Actualizando moto con id: {} y datos: {}", id, moto.toString());
        return serv.guardar(moto);
    }
}