package com.example.Cliente.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.example.Cliente.Model.Cliente;
import com.example.Cliente.Service.ClienteService;

@RestController
@RequestMapping("/api/v1/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService serv;

    @GetMapping
    public List<Cliente> listar() {
        return serv.listar();
    }

    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable Long id) {
        return serv.buscarPorId(id);
    }

    @PostMapping
    public Cliente crear(@RequestBody Cliente c) {
        return serv.guardar(c);
    }
}
