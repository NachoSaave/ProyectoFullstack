package com.example.Pago;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.example.Pago.Client.ClienteFeingClient;
import com.example.Pago.Client.MotoFeingClient;
import com.example.Pago.Model.Pago;
import com.example.Pago.Model.DTO.ClienteDTO;
import com.example.Pago.Model.DTO.MotoDTO;
import com.example.Pago.Repository.pagoRepository;
import com.example.Pago.Service.pagoService;

@SpringBootTest
@ActiveProfiles("test")
public class PagoServiceTest {

    @Autowired
    private pagoService service;

    @MockBean
    private pagoRepository repo;

    @MockBean
    private ClienteFeingClient cliClient;

    @MockBean
    private MotoFeingClient motoClient;

    @Test
    void testGuardar() {

        Pago pago = new Pago();
        pago.setId(1L);
        pago.setSaleId(100L);
        pago.setMonto(7500000.0);
        pago.setMetodo_pago("Tarjeta");
        pago.setEstado("Pagado");
        pago.setClienteId(1L);
        pago.setMotoId(2L);

        when(repo.save(pago)).thenReturn(pago);

        Pago resultado = service.save(pago);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals(100L, resultado.getSaleId());
        assertEquals(7500000.0, resultado.getMonto());
        assertEquals("Tarjeta", resultado.getMetodo_pago());
        assertEquals("Pagado", resultado.getEstado());
        assertEquals(1L, resultado.getClienteId());
        assertEquals(2L, resultado.getMotoId());

        verify(repo, times(1)).save(pago);
    }

    @Test
    void testListar() {

        Pago pago = new Pago();
        pago.setId(1L);
        pago.setSaleId(100L);
        pago.setMonto(7500000.0);
        pago.setMetodo_pago("Tarjeta");
        pago.setEstado("Pagado");
        pago.setClienteId(1L);
        pago.setMotoId(2L);

        when(repo.findAll()).thenReturn(List.of(pago));

        List<Pago> lista = service.findAll();

        assertNotNull(lista);
        assertEquals(1, lista.size());
        assertEquals(7500000.0, lista.get(0).getMonto());
        assertEquals("Tarjeta", lista.get(0).getMetodo_pago());

        verify(repo, times(1)).findAll();
    }

    @Test
    void testBuscarId() {

        Long id = 1L;

        Pago pago = new Pago();
        pago.setId(id);
        pago.setSaleId(100L);
        pago.setMonto(7500000.0);
        pago.setMetodo_pago("Tarjeta");
        pago.setEstado("Pagado");
        pago.setClienteId(1L);
        pago.setMotoId(2L);

        when(repo.findById(id)).thenReturn(Optional.of(pago));

        Pago resultado = service.findById(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals(7500000.0, resultado.getMonto());
        assertEquals("Tarjeta", resultado.getMetodo_pago());

        verify(repo, times(1)).findById(id);
    }

    @Test
    void testObtenerPagoConDetalles() {

        Long id = 1L;

        Pago pago = new Pago();
        pago.setId(id);
        pago.setSaleId(100L);
        pago.setMonto(7500000.0);
        pago.setMetodo_pago("Tarjeta");
        pago.setEstado("Pagado");
        pago.setClienteId(1L);
        pago.setMotoId(2L);

        ClienteDTO cliente = new ClienteDTO();
        MotoDTO moto = new MotoDTO();

        when(repo.findById(id)).thenReturn(Optional.of(pago));
        when(cliClient.getClienteById(1L)).thenReturn(cliente);
        when(motoClient.getMotoById(2L)).thenReturn(moto);

        Map<String, Object> respuesta = service.obtenerPagoConDetalles(id);

        assertNotNull(respuesta);
        assertEquals(pago, respuesta.get("pago"));
        assertEquals(cliente, respuesta.get("cliente"));
        assertEquals(moto, respuesta.get("moto"));

        verify(repo, times(1)).findById(id);
        verify(cliClient, times(1)).getClienteById(1L);
        verify(motoClient, times(1)).getMotoById(2L);
    }
}