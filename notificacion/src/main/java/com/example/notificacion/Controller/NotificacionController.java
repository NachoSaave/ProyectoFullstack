package com.example.notificacion.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.notificacion.Model.Notificacion;
import com.example.notificacion.Service.NotificacionService;

@RestController
@RequestMapping("api/v1/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService serv;

    @GetMapping
    public List<Notificacion> listarNotis(){
        return serv.listarNotis();
    }

    @PostMapping
    private Notificacion guardarNoti(Notificacion noti){
        return serv.guardarNoti(noti);
    }

    @GetMapping("/{id}")
    private Notificacion buscarNotiId(Long id){
        return serv.buscarNotiPorId(id);
    }

    @PutMapping
    private Notificacion actualizarNoti(Notificacion noti){
        return serv.actualizarNoti(noti);
    }

    @DeleteMapping
    private void eliminarNoti(Notificacion noti){
        serv.eliminarNoti(noti);
    }
}
