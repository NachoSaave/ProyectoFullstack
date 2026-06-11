package com.example.Pago.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.example.Pago.Client.ClienteFeingClient;
import com.example.Pago.Client.MotoFeingClient;
import com.example.Pago.Model.Pago;
import com.example.Pago.Model.DTO.*;
import com.example.Pago.Repository.pagoRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class pagoService {
    @Autowired
    private  pagoRepository repo;
    @Autowired
    private ClienteFeingClient cliClient;

    @Autowired
    private MotoFeingClient motoClient;

    //guardar
    public Pago save(Pago pago) {
        return repo.save(pago);
    }
    //listar
    public List<Pago> findAll() {
        return repo.findAll();
    }
    //eliminar
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
    //actualizar
    public Pago update(Pago pago) {
        return repo.save(pago);
    }
    //buscar por id
    public Pago findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Map<String, Object> obtenerPagoConDetalles(Long id) {
        Pago pago = repo.findById(id).orElse(null);
            
        Map<String, Object> respuesta = new HashMap<>();
        if(pago != null) {

            ClienteDTO cliDto =cliClient.getClienteById(pago.getClienteId());
            MotoDTO moDTO = motoClient.getMotoById(pago.getMotoId());

            respuesta.put("pago", pago);
            respuesta.put("cliente", cliDto);
            respuesta.put("moto", moDTO);
        }
        
        return respuesta;
    }
}