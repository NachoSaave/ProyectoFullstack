package com.example.notificacion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

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

@SpringBootTest
@ActiveProfiles("test")
public class NotificacionServiceTest {

    @Autowired
    private NotificacionService service;

    @MockBean
    private NotificacionRepository repo;

    @Test
    public void testGuardarNoti() {

        Notificacion noti = new Notificacion(
                1L,
                "Su pedido ha sido enviado",
                "EMAIL"
        );

        when(repo.save(noti)).thenReturn(noti);

        Notificacion resultado = service.guardarNoti(noti);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Su pedido ha sido enviado", resultado.getMensaje());
        assertEquals("EMAIL", resultado.getTipo());

        verify(repo, times(1)).save(noti);
    }

    @Test
    public void testListarNotis() {

        Notificacion noti = new Notificacion(
                1L,
                "Su pedido ha sido enviado",
                "EMAIL"
        );

        when(repo.findAll()).thenReturn(List.of(noti));

        List<Notificacion> lista = service.listarNotis();

        assertNotNull(lista);
        assertEquals(1, lista.size());
        assertEquals("Su pedido ha sido enviado", lista.get(0).getMensaje());
        assertEquals("EMAIL", lista.get(0).getTipo());

        verify(repo, times(1)).findAll();
    }

    @Test
    public void testBuscarNotiPorId() {

        Long id = 1L;

        Notificacion noti = new Notificacion(
                id,
                "Su pedido ha sido enviado",
                "EMAIL"
        );

        when(repo.findById(id)).thenReturn(Optional.of(noti));

        Notificacion resultado = service.buscarNotiPorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals("Su pedido ha sido enviado", resultado.getMensaje());
        assertEquals("EMAIL", resultado.getTipo());

        verify(repo, times(1)).findById(id);
    }

    @Test
    public void testActualizarNoti() {

        Notificacion noti = new Notificacion(
                1L,
                "Su pedido fue entregado",
                "SMS"
        );

        when(repo.save(noti)).thenReturn(noti);

        Notificacion resultado = service.actualizarNoti(noti);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Su pedido fue entregado", resultado.getMensaje());
        assertEquals("SMS", resultado.getTipo());

        verify(repo, times(1)).save(noti);
    }

    @Test
    public void testEliminarNoti() {

        Long id = 1L;

        doNothing().when(repo).deleteById(id);

        service.eliminarNoti(id);

        verify(repo, times(1)).deleteById(id);
    }
}