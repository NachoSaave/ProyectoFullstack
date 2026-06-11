package com.example.notificacion.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.notificacion.Model.Notificacion;
import com.example.notificacion.Repository.NotificacionRepository;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository repo;

    //guardar noti
    public Notificacion guardarNoti(Notificacion noti){
        return repo.save(noti);
    }
    //listar notis
    public List<Notificacion> listarNotis(){
        return repo.findAll();
    }
    //eliminar noti
    public void eliminarNoti(Notificacion noti){
        repo.delete(noti);
    }
    //actualizar noti
    public Notificacion actualizarNoti(Notificacion noti){
        return repo.save(noti);
    }
    //buscar noti por id
    public Notificacion buscarNotiPorId(Long id){
        return repo.findById(id).orElse(null);
    }
}