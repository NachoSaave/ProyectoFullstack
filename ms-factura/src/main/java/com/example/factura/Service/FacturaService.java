package com.example.factura.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.factura.Client.ClienteFeignClient;
import com.example.factura.Model.Factura;
import com.example.factura.Model.DTO.ClienteDTO;
import com.example.factura.Repository.FacturaRepository;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository repo;

    @Autowired
    private ClienteFeignClient client;
    //guardar
    public Factura guardar(Factura factura){
        return repo.save(factura);
    }

    //listar
    public List<Factura> listar(){
        return repo.findAll();
    }

    //actualizar
    public Factura actualizar(Factura factura){
        return repo.save(factura);
    }

    //eliminar
    public void eliminar(Long id){
        repo.deleteById(id);
    }

    //buscar id
    public Factura buscarId(Long id){
        return repo.findById(id).orElse(null);
    }

    //obtener factura + cliente
    public Map<String, Object> obtenerFacturaYCliente(Long id){
        Factura factura = repo.findById(id).orElse(null);

        Map<String, Object> respuesta = new HashMap<>();
        if(factura != null){
            ClienteDTO cliente = client.getClienteById(factura.getIdCliente());
            respuesta.put("factura", factura);
            respuesta.put("cliente", cliente);
        }

        return respuesta;
    }
}