package com.example.Pago;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.Pago.Model.Pago;
import com.example.Pago.Repository.pagoRepository;

import net.datafaker.Faker;

@Component
public class DataLoader implements CommandLineRunner{
    @Autowired
    private pagoRepository repo;
    
    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        for (int i = 0; i < 15; i++) {
            Pago pago = new Pago();
            pago.setSaleId((long) faker.number().numberBetween(1, 15));
            pago.setMonto(faker.number().randomDouble(0, 100000, 50000000));
            pago.setMetodo_pago(faker.options().option("Tarjeta de Crédito", "Webpay", "Transferencia Bancaria"));
            pago.setEstado(faker.options().option("Pendiente", "Pagado", "Cancelado"));
            pago.setClienteId((long) faker.number().numberBetween(1, 15));
            pago.setMotoId((long) faker.number().numberBetween(1, 15));
            repo.save(pago);
        }
        repo.flush();    
        List<Pago> pagos = repo.findAll();
    }
}