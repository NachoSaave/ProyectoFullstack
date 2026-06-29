package inventario.inventario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import inventario.inventario.Model.inventario;
import inventario.inventario.Repository.inventarioRepository;
import net.datafaker.Faker;

@Component
public class DataLoader implements CommandLineRunner{

    @Autowired
    private inventarioRepository repo;
    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        for (int i = 0; i < 15; i++) {
            inventario inv = new inventario();
            inv.setIdMoto((long) faker.number().numberBetween(1, 100));
            inv.setStock(faker.number().numberBetween(1, 100));
            repo.save(inv);
        }
        repo.flush();
        List<inventario> inventorys = repo.findAll();    
    }
}