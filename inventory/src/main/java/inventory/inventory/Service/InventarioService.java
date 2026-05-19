package inventory.inventory.Service;

import java.util.List;

import org.springframework.stereotype.Service;


import inventory.inventory.Model.inventory;
import inventory.inventory.Repository.Repositoryinventory;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventarioService {

    private final Repositoryinventory repository;

    // LISTAR
    public List<inventory> listar(){
        return repository.findAll();
    }

    // BUSCAR POR ID
    public inventory buscarPorIdMoto(Long idMoto){
        return repository.findByIdMoto(idMoto).orElse(null);
    }

    // GUARDAR
    public inventory guardar(inventory inventario){
        return repository.save(inventario);
    }

    // ELIMINAR
        public void eliminar(Long idMoto){
    inventory inv = repository.findByIdMoto(idMoto).orElse(null);

    if(inv != null){
        repository.deleteById(inv.getId());
    }
}
}