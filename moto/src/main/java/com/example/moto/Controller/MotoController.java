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

@RestController
@RequestMapping("/api/v1/motos")
public class MotoController {

    @Autowired
    private MotoService serv;

    @GetMapping
    public List<Moto> mostrarMotos(){
        return serv.mostrar();
    }

    @PostMapping
    public Moto guardar(@RequestBody Moto moto){
        return serv.guardar(moto);
    }

    @GetMapping("/{id}") 
    public Moto buscarPorId(@PathVariable Long id){
        return serv.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Moto actualizar(@PathVariable Long id, @RequestBody Moto moto){
        Moto motoExistente = serv.buscarPorId(id);
        if (motoExistente != null) {
            motoExistente.setMarca(moto.getMarca());
            motoExistente.setModelo(moto.getModelo());
            motoExistente.setPrecio(moto.getPrecio());
            motoExistente.setAno(moto.getAno());
            motoExistente.setCc(moto.getCc());
            return serv.actualizar(motoExistente);
        } else {
            return null; 
        }
    }
}