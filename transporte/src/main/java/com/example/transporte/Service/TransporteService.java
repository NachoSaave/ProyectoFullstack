package com.example.transporte.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.transporte.Model.Transporte;
import com.example.transporte.Repository.TransporteRepository;

@Service
public class TransporteService {

    @Autowired
    private TransporteRepository repo;

    //guardar
    public Transporte guardarTran(Transporte trans){
        return repo.save(trans);
    }

    //obtener por id
    public Transporte buscarId(Long id){
        return repo.findById(id).orElse(null);
    }

    //listar todos
    public List<Transporte> listar(){
        return repo.findAll();
    }

    //actualizar
    public Transporte actualizarTran(Transporte trans){
        return repo.save(trans);
    }

    //eliminar
    public void eliminarTran(Long id){
        repo.deleteById(id);
    }

    //listar por empresa
    public List<Transporte> listarPorEmpresa(String empresa){
        return repo.findByEmpresa(empresa);
    }

}