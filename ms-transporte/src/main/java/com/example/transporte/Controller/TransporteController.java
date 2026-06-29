package com.example.transporte.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.transporte.Model.Transporte;
import com.example.transporte.Service.TransporteService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/transportes")
public class TransporteController {

    @Autowired
    private TransporteService serv;

    @GetMapping
    public List<Transporte> listar(){
        log.info("INFORMACION: Obteniendo lista de transportes");
        return serv.listar();
    }

    @GetMapping("/{id}")
    public Transporte buscarId(@PathVariable Long id){
        log.info("INFORMACION: Buscando transporte con ID: {}", id);
        return serv.buscarId(id);
    }

    @PostMapping
    public Transporte guardar(@RequestBody Transporte trans){
        log.info("INFORMACION: Guardando nuevo transporte con datos: {}", trans.toString());
        return serv.guardarTran(trans);
    }

    @PutMapping("/{id}")
    public Transporte actualizar(@PathVariable Long id, @RequestBody Transporte trans){
        log.info("INFORMACION: Actualizando transporte con ID: {} con nuevos datos: {}", id, trans.toString());
        return serv.actualizarTran(trans);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        log.info("INFORMACION: Eliminando transporte con ID: {}", id);
        serv.eliminarTran(id);
    }

    @GetMapping("/empresa/{empresa}")
    public List<Transporte> listarPorEmpresa(@PathVariable String empresa){
        log.info("INFORMACION: Listando transportes de empresa: {}", empresa);
        return serv.listarPorEmpresa(empresa);
    }
}
