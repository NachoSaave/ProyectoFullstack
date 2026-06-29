package inventario.inventario.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import inventario.inventario.Model.inventario;
import inventario.inventario.Repository.inventarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventarioService {

    private final inventarioRepository repository;

    // LISTAR
    public List<inventario> listar(){
        return repository.findAll();
    }

    // BUSCAR POR ID
    public inventario buscarPorIdMoto(Long idMoto){
        return repository.findByIdMoto(idMoto).orElse(null);
    }

    // GUARDAR
    public inventario guardar(inventario inventario){
        return repository.save(inventario);
    }

    // ELIMINAR
        public void eliminar(Long idMoto){
    inventario inv = repository.findByIdMoto(idMoto).orElse(null);

    if(inv != null){
        repository.deleteById(inv.getId());
    }
}
}