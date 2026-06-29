package inventory.inventory.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import inventory.inventory.Model.inventory;
import inventory.inventory.Service.InventarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioService service;

    // LISTAR
    @GetMapping
    public List<inventory> listar(){
        log.info("INFORMACION: Se solicita lista de inventario");
        return service.listar();
    }

    // BUSCAR POR ID
    @GetMapping("/{idMoto}")
    public inventory buscarPorId(@PathVariable Long idMoto){
        log.info("INFORMACION: Se solicita inventario por idMoto {}", idMoto);
        return service.buscarPorIdMoto(idMoto);
    }

    // GUARDAR
    @PostMapping
    public inventory guardar(@RequestBody inventory inventario){
        log.info("INFORMACION: Se crea inventario con los siguientes datos: {}", inventario.toString());
        return service.guardar(inventario);
    }

    // ACTUALIZAR
    @PutMapping("/{idMoto}")
    public inventory actualizar(@PathVariable Long idMoto, @RequestBody inventory inventario){
        log.info("INFORMACION: Se actualiza inventario por idMoto {} con los siguientes datos: {}", idMoto, inventario.toString());
        return service.guardar(inventario);
    }

    // ELIMINAR
    @DeleteMapping("/{idMoto}")
    public void eliminar(@PathVariable Long idMoto){
        log.info("INFORMACION: Se elimina inventario por idMoto {}", idMoto);
        service.eliminar(idMoto);
    }
}