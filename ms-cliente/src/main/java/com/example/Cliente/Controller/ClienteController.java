package com.example.Cliente.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.example.Cliente.Model.Cliente;
import com.example.Cliente.Service.ClienteService;

@Slf4j
@RestController
@RequestMapping("/api/v1/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService serv;

    @GetMapping
    public List<Cliente> listar() {
        log.info("INFORMACION: Se solicita lista de clientes");
        return serv.listar();
    }

    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable Long id) {
        log.info("INFORMACION: Se solicita cliente por id {}", id);
        return serv.buscarPorId(id);
    }

    @PostMapping
    public Cliente crear(@RequestBody Cliente c) {
        log.info("INFORMACION: Se crea cliente con los siguientes datos: {}", c.toString());
        return serv.guardar(c);
    } 

    @DeleteMapping
    public void String(@PathVariable Long id){
        log.info("INFORMACION: Se ha eliminado el cliente con id: {}", id);
        serv.eliminar(id);
    }

    @PutMapping
    public Cliente actualizar(@PathVariable Long id, @RequestBody Cliente cli){
        log.info("INFORMACION: Se ha actualizado cliente con id: {} y atributos: {}", id, cli);
        return serv.actualizar(cli);
    }
}
