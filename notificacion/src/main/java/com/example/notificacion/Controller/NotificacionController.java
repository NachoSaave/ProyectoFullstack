package com.example.notificacion.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.notificacion.Model.Notificacion;
import com.example.notificacion.Service.NotificacionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/v1/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService serv;

    @GetMapping
    public List<Notificacion> listarNotis(){
        log.info("INFORMACION: Obteniendo lista de notificaciones");
        return serv.listarNotis();
    }

    @PostMapping
    private Notificacion guardarNoti(Notificacion noti){
        log.info("INFORMACION: Guardando nueva notificacion: {}", noti.toString());
        return serv.guardarNoti(noti);
    }

    @GetMapping("/{id}")
    private Notificacion buscarNotiId(@PathVariable Long id){
        log.info("INFORMACION: Buscando notificacion con ID: {}", id);
        return serv.buscarNotiPorId(id);
    }

    @PutMapping("/{id}")
    private Notificacion actualizarNoti(@PathVariable Long id, @RequestBody Notificacion noti){
        log.info("INFORMACION: Actualizando notificacion de id: {} con los datos: {}", id, noti.toString());
        return serv.actualizarNoti(noti);
    }

    @DeleteMapping("/{id}")
    private void eliminarNoti(@PathVariable Long id){
        log.info("INFORMACION: Eliminando notificacion de id: {}", id);
        serv.eliminarNoti(id);
    }
}
