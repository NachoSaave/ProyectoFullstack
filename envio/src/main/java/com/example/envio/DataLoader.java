package com.example.envio;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.example.envio.Model.Envio;
import com.example.envio.Repository.EnvioRepository;

import net.datafaker.Faker;

public class DataLoader implements CommandLineRunner{

    @Autowired
    private EnvioRepository repo;
    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        for (int i = 0; i < 15; i++) {
            Envio env = new Envio();
            env.setTiempoEstimado(faker.date().future(10, java.util.concurrent.TimeUnit.DAYS).toString());
            env.setRutReceptor(faker.idNumber().valid());
            env.setIdTransporte((long) faker.number().numberBetween(1, 100));
            env.setIdDestino((long) faker.number().numberBetween(1, 100));
            repo.save(env);
        }
        repo.flush();
        List<Envio> envios = repo.findAll();    
    }

}
