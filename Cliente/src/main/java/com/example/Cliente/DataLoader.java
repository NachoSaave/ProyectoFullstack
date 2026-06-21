package com.example.Cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.Cliente.Model.Cliente;
import com.example.Cliente.Repository.ClienteRepository;

import net.datafaker.Faker;

@Component
public class DataLoader implements CommandLineRunner{
    @Autowired  
    private ClienteRepository repo;
    
    @Override
    public void run(String... args) throws Exception {
    // Solo meter datos falsos si la base de datos está totalmente vacía
    if (repo.count() == 0) {
        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            Cliente cliente = new Cliente();
            cliente.setNombre(faker.name().fullName());
            cliente.setTelefono(faker.phoneNumber().cellPhone());
            repo.save(cliente);
        }
    }
    repo.flush();
    List<Cliente> clientes = repo.findAll();
}
}
