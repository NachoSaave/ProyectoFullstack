package com.example.Pago;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
@ActiveProfiles("test")
@SpringBootTest
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

        when(repo.save(pago)).thenReturn(pago);

        Pago resultado = service.save(pago);

        assertNotNull(resultado);
    }

    @Test
    void testListar() {
        when(repo.findAll()).thenReturn(List.of(new Pago()));

        assertEquals(1, service.findAll().size());
    }

    @Test
    void testBuscarId() {
        Pago pago = new Pago();

        when(repo.findById(1L)).thenReturn(Optional.of(pago));

        assertNotNull(service.findById(1L));
    }

    @Test
    void testObtenerPagoConDetalles() {

        Pago pago = new Pago();
        pago.setClienteId(1L);
        pago.setMotoId(1L);

        ClienteDTO cliente = new ClienteDTO();
        MotoDTO moto = new MotoDTO();

        when(repo.findById(1L)).thenReturn(Optional.of(pago));
        when(cliClient.getClienteById(1L)).thenReturn(cliente);
        when(motoClient.getMotoById(1L)).thenReturn(moto);

        Map<String,Object> respuesta = service.obtenerPagoConDetalles(1L);

        assertNotNull(respuesta);
    }
}