package com.example.factura.Controller;

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

import com.example.factura.Model.Factura;
import com.example.factura.Service.FacturaService;

@RestController
@RequestMapping("/api/v1/facturas")
public class FacturaController {
    @Autowired
    private FacturaService serv;

    @GetMapping
    public List<Factura> listar(){
        return serv.listar();
    }

    @PostMapping
    public Factura guardar(@RequestBody Factura factura){
        return serv.guardar(factura);
    }

    @GetMapping("/{id}")
    public Factura buscarId(@PathVariable Long id){
        return serv.buscarId(id);
    }

    @PutMapping("/{id}")
    public Factura actualizar(@PathVariable Long id, @RequestBody Factura factura){
        return serv.actualizar(factura);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        serv.eliminar(id);
    }

    //factura + cliente
    @GetMapping("/cliente/{id}")
    public Map<String, Object> obtenerFacturaYCliente(@PathVariable Long id){
        return serv.obtenerFacturaYCliente(id);
    }
}