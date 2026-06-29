package com.example.transporte;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.transporte.Model.Transporte;
import com.example.transporte.Repository.TransporteRepository;

import net.datafaker.Faker;

@Component
public class DataLoader implements CommandLineRunner{
    @Autowired
    private TransporteRepository repo;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        for (int i = 0; i < 15; i++) {
            String nombreConductor = faker.name().fullName();
            String empresa = faker.company().name();
            String tipoTransporte = faker.options().option("Camión", "Tren", "Furgoneta", "Motocicleta", "Bicicleta");

            Transporte transporte = new Transporte();
            transporte.setNombreConductor(nombreConductor);
            transporte.setEmpresa(empresa);
            transporte.setTipoTransporte(tipoTransporte);

            repo.save(transporte);
        }
        repo.flush();
        List<Transporte> transportes = repo.findAll();
    }
}
