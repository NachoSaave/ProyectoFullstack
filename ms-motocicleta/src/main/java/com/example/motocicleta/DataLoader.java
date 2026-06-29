package com.example.motocicleta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.motocicleta.Model.Moto;
import com.example.motocicleta.Repository.MotoRepository;

import net.datafaker.Faker;

@Component
public class DataLoader implements CommandLineRunner{
    @Autowired
    private MotoRepository repo;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        // Hardcoding de combinaciones reales: Marca, Modelo, CC y precio
        String[][] motosReales = {
            {"Honda", "CBR600RR", "600", "15490000"},
            {"Honda", "Africa Twin", "1100", "19890000"},
            {"Yamaha", "YZF-R3", "321", "6190000"},
            {"Kawasaki", "Ninja 400", "399", "6790000"},
            {"Suzuki", "GSX-R1000", "1000", "18990000"},
            {"BMW", "R 1250 GS", "1254", "24500000"},
            {"Ducati", "Panigale V2", "955", "23900000"},
            {"KTM", "Duke 390", "373", "5290000"},
            {"Harley-Davidson", "Street Glide", "1746", "31900000"},
            {"Aprilia", "RSV4", "1099", "25500000"},
            {"Royal Enfield", "Interceptor 650", "648", "6290000"}
        };

        // Lista harcodeada para que el datafaker los agregue
        for (String[] datos : motosReales) {
            Moto moto = new Moto();
            moto.setMarca(datos[0]);
            moto.setModelo(datos[1]);
            moto.setCc(Integer.parseInt(datos[2]));
            moto.setPrecio(Double.parseDouble(datos[3]));

            // Año aleatorio, lo elije el datafaker
            moto.setAno(faker.number().numberBetween(2018, 2026));
            repo.save(moto);
        }
    }
}