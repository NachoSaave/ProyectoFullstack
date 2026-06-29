package com.example.notificacion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.example.notificacion.Model.Notificacion;
import com.example.notificacion.Repository.NotificacionRepository;
import com.example.notificacion.Service.NotificacionService;
@ActiveProfiles("test")
@SpringBootTest
public class NotificacionServiceTest {
@Autowired
    private NotificacionService service;

    @MockBean
    private NotificacionRepository repo;

    @Test
    public void testGuardarNoti() {
        Notificacion noti = new Notificacion();
        noti.setId(1L);

        when(repo.save(noti)).thenReturn(noti);

        Notificacion resultado = service.guardarNoti(noti);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    public void testListarNotis() {
        Notificacion noti = new Notificacion();
        noti.setId(1L);

        when(repo.findAll()).thenReturn(List.of(noti));

        List<Notificacion> lista = service.listarNotis();

        assertEquals(1, lista.size());
    }

    @Test
    public void testBuscarNotiPorId() {
        Notificacion noti = new Notificacion();
        noti.setId(1L);

        when(repo.findById(1L)).thenReturn(Optional.of(noti));

        Notificacion resultado = service.buscarNotiPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    public void testActualizarNoti() {
        Notificacion noti = new Notificacion();
        noti.setId(1L);

        when(repo.save(noti)).thenReturn(noti);

        Notificacion resultado = service.actualizarNoti(noti);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    public void testEliminarNoti() {
        Long id = 1L;

        doNothing().when(repo).deleteById(id);

        service.eliminarNoti(id);
    }
}

