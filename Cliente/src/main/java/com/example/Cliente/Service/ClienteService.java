package com.example.Cliente.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.example.Cliente.Model.Cliente;
import com.example.Cliente.Repository.ClienteRepository;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repo;

    public List<Cliente> listar() {
        return repo.findAll();
    }

    public Cliente guardar(Cliente c) {
        return repo.save(c);
    }

    public Cliente buscarPorId(Long id) {
        return repo.findById(id).orElse(null);
    }
}