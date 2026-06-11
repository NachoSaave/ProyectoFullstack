package com.example.notificacion.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.notificacion.Model.Notificacion;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long>{
}
