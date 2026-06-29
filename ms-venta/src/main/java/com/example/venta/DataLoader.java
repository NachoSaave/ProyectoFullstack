package com.example.venta;

import java.time.ZoneId;
import java.util.List;
import java.util.concurrent.TimeUnit;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.venta.Model.venta;
import com.example.venta.Repository.ventaRepository;

import net.datafaker.Faker;

@Component
public class DataLoader implements CommandLineRunner{
    @Autowired
    private ventaRepository repo;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        for (int i = 0; i < 15; i++) {
        venta venta = new venta();
        venta.setIdCliente((long) faker.number().numberBetween(1, 100));
        venta.setIdMoto((long) faker.number().numberBetween(1, 50));
        venta.setTotal(faker.number().randomDouble(2, 1000, 10000));
        venta.setEstado(faker.options().option("COMPLETADA", "PENDIENTE", "CANCELADA"));
        venta.setFechaVenta(faker.date().past(30, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        repo.save(venta);
    }
    repo.flush();
    List<venta> ventas = repo.findAll();
    }
}
