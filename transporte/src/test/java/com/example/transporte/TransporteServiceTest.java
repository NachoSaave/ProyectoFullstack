package com.example.transporte;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.transporte.Model.Transporte;
import com.example.transporte.Repository.TransporteRepository;
import com.example.transporte.Service.TransporteService;

@SpringBootTest
public class TransporteServiceTest {

    @Autowired
    private TransporteService service;

    @MockBean
    private TransporteRepository repo;

    @Test
    void testGuardar() {

        Transporte trans = new Transporte();

        when(repo.save(trans)).thenReturn(trans);

        assertNotNull(service.guardarTran(trans));
    }

    @Test
    void testBuscarId() {

        Transporte trans = new Transporte();

        when(repo.findById(1L)).thenReturn(Optional.of(trans));

        assertNotNull(service.buscarId(1L));
    }

    @Test
    void testListar() {

        when(repo.findAll()).thenReturn(List.of(new Transporte()));

        assertEquals(1, service.listar().size());
    }

    @Test
    void testBuscarEmpresa() {

        when(repo.findByEmpresa("Chilexpress"))
                .thenReturn(List.of(new Transporte()));

        assertEquals(1, service.listarPorEmpresa("Chilexpress").size());
    }
}