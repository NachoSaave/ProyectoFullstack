package inventory.inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import inventory.inventory.Model.inventory;
import inventory.inventory.Repository.Repositoryinventory;
import net.datafaker.Faker;

@Component
public class DataLoader implements CommandLineRunner{

    @Autowired
    private Repositoryinventory repo;
    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        for (int i = 0; i < 15; i++) {
            inventory inv = new inventory();
            inv.setIdMoto((long) faker.number().numberBetween(1, 100));
            inv.setStock(faker.number().numberBetween(1, 100));
            repo.save(inv);
        }
        repo.flush();
        List<inventory> inventorys = repo.findAll();    
    }
}