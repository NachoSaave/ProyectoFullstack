package com.example.transporte;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.example.transporte.Model.Transporte;
import com.example.transporte.Repository.TransporteRepository;
import com.example.transporte.Service.TransporteService;

@SpringBootTest
@ActiveProfiles("test")
public class TransporteServiceTest {

    @Autowired
    private TransporteService service;

    @MockBean
    private TransporteRepository repo;

    @Test
    void testGuardar() {

        Transporte trans = new Transporte(
                1L,
                "Juan Pérez",
                "Chilexpress",
                "Camión"
        );

        when(repo.save(trans)).thenReturn(trans);

        Transporte resultado = service.guardarTran(trans);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Juan Pérez", resultado.getNombreConductor());
        assertEquals("Chilexpress", resultado.getEmpresa());
        assertEquals("Camión", resultado.getTipoTransporte());

        verify(repo, times(1)).save(trans);
    }

    @Test
    void testBuscarId() {

        Long id = 1L;

        Transporte trans = new Transporte(
                id,
                "Juan Pérez",
                "Chilexpress",
                "Camión"
        );

        when(repo.findById(id)).thenReturn(Optional.of(trans));

        Transporte resultado = service.buscarId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals("Juan Pérez", resultado.getNombreConductor());
        assertEquals("Chilexpress", resultado.getEmpresa());
        assertEquals("Camión", resultado.getTipoTransporte());

        verify(repo, times(1)).findById(id);
    }

    @Test
    void testListar() {

        Transporte trans = new Transporte(
                1L,
                "Juan Pérez",
                "Chilexpress",
                "Camión"
        );

        when(repo.findAll()).thenReturn(List.of(trans));

        List<Transporte> lista = service.listar();

        assertNotNull(lista);
        assertEquals(1, lista.size());
        assertEquals("Juan Pérez", lista.get(0).getNombreConductor());
        assertEquals("Chilexpress", lista.get(0).getEmpresa());
        assertEquals("Camión", lista.get(0).getTipoTransporte());

        verify(repo, times(1)).findAll();
    }

    @Test
    void testBuscarEmpresa() {

        Transporte trans = new Transporte(
                1L,
                "Juan Pérez",
                "Chilexpress",
                "Camión"
        );

        when(repo.findByEmpresa("Chilexpress"))
                .thenReturn(List.of(trans));

        List<Transporte> lista = service.listarPorEmpresa("Chilexpress");

        assertNotNull(lista);
        assertEquals(1, lista.size());
        assertEquals("Juan Pérez", lista.get(0).getNombreConductor());
        assertEquals("Chilexpress", lista.get(0).getEmpresa());
        assertEquals("Camión", lista.get(0).getTipoTransporte());

        verify(repo, times(1)).findByEmpresa("Chilexpress");
    }
}