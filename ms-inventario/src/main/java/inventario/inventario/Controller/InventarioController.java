package inventario.inventario.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import inventario.inventario.Model.inventario;
import inventario.inventario.Service.InventarioService;
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
    public List<inventario> listar(){
        log.info("INFORMACION: Se solicita lista de inventario");
        return service.listar();
    }

    // BUSCAR POR ID
    @GetMapping("/{idMoto}")
    public inventario buscarPorId(@PathVariable Long idMoto){
        log.info("INFORMACION: Se solicita inventario por idMoto {}", idMoto);
        return service.buscarPorIdMoto(idMoto);
    }

    // GUARDAR
    @PostMapping
    public inventario guardar(@RequestBody inventario inventario){
        log.info("INFORMACION: Se crea inventario con los siguientes datos: {}", inventario.toString());
        return service.guardar(inventario);
    }

    // ACTUALIZAR
    @PutMapping("/{idMoto}")
    public inventario actualizar(@PathVariable Long idMoto, @RequestBody inventario inventario){
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