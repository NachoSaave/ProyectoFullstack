package com.example.destino;

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

import com.example.destino.Model.Destino;
import com.example.destino.Repository.DestinoRepository;
import com.example.destino.Service.DestinoService;

@SpringBootTest
@ActiveProfiles("test")
public class DestinoServiceTest {

    @Autowired
    private DestinoService destinoService;

    @MockBean
    private DestinoRepository destinoRepository;

    @Test
    public void testGuardar() {

        Destino destino = new Destino(
                1L,
                "Av. Libertador",
                1234,
                "Santiago Centro",
                "Santiago"
        );

        when(destinoRepository.save(destino)).thenReturn(destino);

        Destino saved = destinoService.saveDestino(destino);

        assertNotNull(saved);
        assertEquals(1L, saved.getId());
        assertEquals("Av. Libertador", saved.getNombreCalle());
        assertEquals(1234, saved.getNumeroCalle());
        assertEquals("Santiago Centro", saved.getComuna());
        assertEquals("Santiago", saved.getCiudad());

        verify(destinoRepository, times(1)).save(destino);
    }

    @Test
    public void testBuscarPorId() {

        Long id = 1L;

        Destino destino = new Destino(
                id,
                "Av. Libertador",
                1234,
                "Santiago Centro",
                "Santiago"
        );

        when(destinoRepository.findById(id)).thenReturn(Optional.of(destino));

        Destino found = destinoService.getDestinoById(id);

        assertNotNull(found);
        assertEquals(id, found.getId());
        assertEquals("Av. Libertador", found.getNombreCalle());
        assertEquals(1234, found.getNumeroCalle());
        assertEquals("Santiago Centro", found.getComuna());
        assertEquals("Santiago", found.getCiudad());

        verify(destinoRepository, times(1)).findById(id);
    }

    @Test
    public void testActualizar() {

        Destino destino = new Destino(
                1L,
                "Av. Providencia",
                2500,
                "Providencia",
                "Santiago"
        );

        when(destinoRepository.save(destino)).thenReturn(destino);

        Destino updated = destinoService.updateDestino(destino);

        assertNotNull(updated);
        assertEquals(1L, updated.getId());
        assertEquals("Av. Providencia", updated.getNombreCalle());
        assertEquals(2500, updated.getNumeroCalle());
        assertEquals("Providencia", updated.getComuna());
        assertEquals("Santiago", updated.getCiudad());

        verify(destinoRepository, times(1)).save(destino);
    }

    @Test
    public void testEliminar() {

        Long id = 1L;

        doNothing().when(destinoRepository).deleteById(id);

        destinoService.deleteDestino(id);

        verify(destinoRepository, times(1)).deleteById(id);
    }

    @Test
    public void testBuscarPorComuna() {

        Destino destino = new Destino(
                1L,
                "Av. Matta",
                500,
                "Santiago Centro",
                "Santiago"
        );

        when(destinoRepository.findByComuna("Santiago Centro"))
                .thenReturn(List.of(destino));

        List<Destino> destinos = destinoService.getDestinoByComuna("Santiago Centro");

        assertNotNull(destinos);
        assertEquals(1, destinos.size());
        assertEquals("Av. Matta", destinos.get(0).getNombreCalle());
        assertEquals(500, destinos.get(0).getNumeroCalle());
        assertEquals("Santiago Centro", destinos.get(0).getComuna());
        assertEquals("Santiago", destinos.get(0).getCiudad());

        verify(destinoRepository, times(1)).findByComuna("Santiago Centro");
    }

    @Test
    public void testBuscarPorCiudad() {

        Destino destino = new Destino(
                1L,
                "Av. Argentina",
                150,
                "Valparaíso",
                "Valparaiso"
        );

        when(destinoRepository.findByCiudad("Valparaiso"))
                .thenReturn(List.of(destino));

        List<Destino> destinos = destinoService.getDestinoByCiudad("Valparaiso");

        assertNotNull(destinos);
        assertEquals(1, destinos.size());
        assertEquals("Av. Argentina", destinos.get(0).getNombreCalle());
        assertEquals(150, destinos.get(0).getNumeroCalle());
        assertEquals("Valparaíso", destinos.get(0).getComuna());
        assertEquals("Valparaiso", destinos.get(0).getCiudad());

        verify(destinoRepository, times(1)).findByCiudad("Valparaiso");
    }
}