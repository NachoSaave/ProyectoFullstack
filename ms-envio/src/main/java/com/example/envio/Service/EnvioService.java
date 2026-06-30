package com.example.envio.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.envio.Client.DestinoFeignClient;
import com.example.envio.Client.TransporteFeignClient;
import com.example.envio.Model.Envio;
import com.example.envio.Model.DTO.DestinoDTO;
import com.example.envio.Model.DTO.TransporteDTO;
import com.example.envio.Repository.EnvioRepository;

@Service
public class EnvioService {
    @Autowired
    private EnvioRepository repo;

    @Autowired
    private DestinoFeignClient DESClient;

    @Autowired
    private TransporteFeignClient TRASClient;

    //guardar
    public Envio save(Envio envio) {
        return repo.save(envio);
    }
    //listar
    public List<Envio> findAll() {
        return repo.findAll();
    }
    //eliminar
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
    //actualizar
    public Envio update(Envio envio) {
        return repo.save(envio);
    }
    //buscar por id
    public Envio findById(Long id) {
        return repo.findById(id).orElse(null);
    }
    //buscar por id con detalle completo
    public Map<String, Object> obtenerEnvioConDetalles(Long id) {
        Envio envio = repo.findById(id).orElse(null);
            
        Map<String, Object> respuesta = new HashMap<>();
        if(envio != null) {

            DestinoDTO desDTO = DESClient.getDestinoById(envio.getIdDestino());
            TransporteDTO transDTO = TRASClient.getTransporteById(envio.getIdTransporte());

            respuesta.put("envio", envio);
            respuesta.put("destino", desDTO);
            respuesta.put("transporte", transDTO);
        }
        
        return respuesta;
    }
}