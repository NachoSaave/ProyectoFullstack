package com.example.notificacion;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.notificacion.Model.Notificacion;
import com.example.notificacion.Repository.NotificacionRepository;

import net.datafaker.Faker;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired  
    private NotificacionRepository repo;

    @Override
    public void run(String... args) throws Exception {
    if (repo.count() == 0) {
        Faker faker = new Faker();
        for (int i = 0; i < 15; i++) {
            Notificacion notificacion = new Notificacion();
            notificacion.setMensaje(faker.lorem().sentence());
            notificacion.setTipo(faker.options().option("INFO", "WARNING", "ERROR"));
            repo.save(notificacion);
        }

    }
    repo.flush();
    List<Notificacion> notificaciones = repo.findAll();
    }
}
