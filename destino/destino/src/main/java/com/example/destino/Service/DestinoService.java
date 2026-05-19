package com.example.destino.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.destino.Model.Destino;
import com.example.destino.Receptor.DestinoRepository;
import java.util.List;

@Service
public class DestinoService {

    @Autowired
    private DestinoRepository repo;

    //guardar
    public Destino saveDestino(Destino destino) {
        return repo.save(destino);
    }

    //listar por id
    public Destino getDestinoById(Long id) {
        return repo.findById(id).orElse(null);
    }

    //actualizar
    public Destino updateDestino(Destino destino) {
        return repo.save(destino);
    }
    //eliminar
    public void deleteDestino(Long id) {
        repo.deleteById(id);
    }

    //buscar por comuna
    public List<Destino> getDestinoByComuna(String comuna) { 
        return repo.findByComuna(comuna);
    }

    //buscar por ciudad
    public List<Destino> getDestinoByCiudad(String ciudad) {
        return repo.findByCiudad(ciudad);
    }
}