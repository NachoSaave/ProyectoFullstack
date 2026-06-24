package com.example.Cliente.Controller;

import com.example.Cliente.Model.Cliente;
import com.example.Cliente.Service.ClienteService;
import com.example.Cliente.assembler.ClienteAssambler;

import lombok.RequiredArgsConstructor;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v2/clientes")
@RequiredArgsConstructor
public class ClienteControllerV2 {

    private final ClienteService serv;
    private final ClienteAssambler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Cliente>> listar() {

        List<EntityModel<Cliente>> clientes = serv.listar()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                clientes,
                linkTo(methodOn(ClienteControllerV2.class).listar()).withSelfRel()
        );
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Cliente> buscarPorId(@PathVariable Long id) {

        Cliente cliente = serv.buscarPorId(id);
        return assembler.toModel(cliente);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Cliente>> crear(@RequestBody Cliente cliente) {

        Cliente nuevoCliente = serv.guardar(cliente);

        return ResponseEntity
                .created(
                        linkTo(methodOn(ClienteControllerV2.class)
                        .buscarPorId(nuevoCliente.getId()))
                        .toUri()
                )
                .body(assembler.toModel(nuevoCliente));
    }
                                                                
    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> eliminar(@PathVariable Long id) {

        serv.eliminar(id);

        return ResponseEntity.noContent().build();
    }   
}