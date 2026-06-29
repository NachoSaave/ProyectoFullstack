package com.example.factura;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.factura.Model.Factura;
import com.example.factura.Repository.FacturaRepository;

import net.datafaker.Faker;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired  
    private FacturaRepository repo;

    @Override
    public void run(String... args) throws Exception {
        // Verifica si la base de datos de facturas está vacía
        if (repo.count() == 0) {
            Faker faker = new Faker();
            
            for (int i = 0; i < 15; i++) {
                Factura factura = new Factura();

                factura.setIdCliente(faker.number().numberBetween(1L, 100L));
                factura.setDescripcion(faker.lorem().sentence());

                repo.save(factura);
            }            
        }
        repo.flush();
        List<Factura> facturas = repo.findAll();
    }
}