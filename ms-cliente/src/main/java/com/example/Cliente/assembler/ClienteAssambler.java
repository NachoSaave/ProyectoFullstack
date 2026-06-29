package com.example.Cliente.assembler;

import com.example.Cliente.Controller.ClienteControllerV2;
import com.example.Cliente.Model.Cliente;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ClienteAssambler implements RepresentationModelAssembler<Cliente, EntityModel<Cliente>> {

    @Override
    public EntityModel<Cliente> toModel(Cliente cliente) {

        return EntityModel.of(
                cliente,
                linkTo(methodOn(ClienteControllerV2.class)
                        .buscarPorId(cliente.getId()))
                        .withSelfRel(),

                linkTo(methodOn(ClienteControllerV2.class)
                        .listar())
                        .withRel("clientes")
        );
    }
}