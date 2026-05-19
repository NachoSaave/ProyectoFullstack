package inventory.inventory.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import inventory.inventory.Model.inventory;
import inventory.inventory.Service.InventarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioService service;

    // LISTAR
    @GetMapping
    public List<inventory> listar(){
        return service.listar();
    }

    // BUSCAR POR ID
    @GetMapping("/{idMoto}")
    public inventory buscarPorId(@PathVariable Long idMoto){
        return service.buscarPorIdMoto(idMoto);
    }

    // GUARDAR
    @PostMapping
    public inventory guardar(@RequestBody inventory inventario){
        return service.guardar(inventario);
    }

    // ACTUALIZAR
    @PutMapping("/{idMoto}")
    public inventory actualizar(
            @PathVariable Long idMoto,
            @RequestBody inventory inventario){

        inventory existente = service.buscarPorIdMoto(idMoto);

        if(existente != null){

            existente.setStock(inventario.getStock());
            existente.setIdMoto(inventario.getIdMoto());

            return service.guardar(existente);
        }

        return null;
    }

    // ELIMINAR
    @DeleteMapping("/{idMoto}")
    public void eliminar(@PathVariable Long idMoto){
        service.eliminar(idMoto);
    }
}