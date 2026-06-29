package com.example.moto.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.moto.Model.Moto;
import com.example.moto.Repository.MotoRepository;

@Service
public class MotoService {

    @Autowired
    private MotoRepository repo;

    public List<Moto> mostrar(){
        return repo.findAll();
    }

    public Moto guardar(Moto moto){
        return repo.save(moto);
    }

    public Moto buscarPorId(Long id){
        return repo.findById(id).orElse(null);
    }

    public Moto actualizar(Moto moto){
        return repo.save(moto);
    }

}