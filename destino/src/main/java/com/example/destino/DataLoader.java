package com.example.destino;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.destino.Model.Destino;
import com.example.destino.Repository.DestinoRepository;

import java.util.Locale;
import net.datafaker.Faker;

@Component
public class DataLoader  implements CommandLineRunner{
    
    @Autowired
    private DestinoRepository repo;
    @Override
    public void run(String... args) throws Exception {
        if(repo.count() == 0) {
            Faker faker = new Faker(Locale.of("es", "CL"));
            for (int i = 0; i < 100; i++) {
                Destino destino = new Destino();
                destino.setNombreCalle(faker.address().streetName());
                destino.setNumeroCalle(faker.number().numberBetween(100,9999));
                destino.setComuna(faker.address().state());
                destino.setCiudad(faker.address().city());
                
                repo.save(destino);
            }
        }
        repo.flush();
        List<Destino> destinos = repo.findAll();
    }
}