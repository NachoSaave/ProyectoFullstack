package com.example.moto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.moto.Model.Moto;
import com.example.moto.Repository.MotoRepository;
import com.example.moto.Service.MotoService;

@SpringBootTest
class MotoServiceTest {

    @Autowired
    private MotoService service;

    @MockBean
    private MotoRepository repo;

    @Test
    void testGuardar() {
        Moto moto = new Moto();
        moto.setId(1L);

        when(repo.save(moto)).thenReturn(moto);

        Moto resultado = service.guardar(moto);

        assertEquals(1L, resultado.getId());
    }

    @Test
    void testBuscarPorId() {
        Moto moto = new Moto();
        moto.setId(1L);

        when(repo.findById(1L)).thenReturn(Optional.of(moto));

        Moto resultado = service.buscarPorId(1L);

        assertEquals(1L, resultado.getId());
    }

    @Test
    void testMostrar() {
        when(repo.findAll()).thenReturn(java.util.List.of(new Moto()));

        assertEquals(1, service.mostrar().size());
    }

    @Test
    void testActualizar() {
        Moto moto = new Moto();
        moto.setId(1L);

        when(repo.save(moto)).thenReturn(moto);

        Moto resultado = service.actualizar(moto);

        assertEquals(1L, resultado.getId());
    }
}