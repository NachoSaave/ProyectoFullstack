package com.example.destino;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.destino.Model.Destino;
import com.example.destino.Repository.DestinoRepository;
import com.example.destino.Service.DestinoService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("test")
public class DestinoServiceTest {
    @Autowired
    private DestinoService destinoService;

    @MockBean
    private DestinoRepository destinoRepository;

    @Test
    public void testGuardar() {
        Destino destinoInput = new Destino();

        when(destinoRepository.save(destinoInput)).thenReturn(destinoInput);

        Destino saved = destinoService.saveDestino(destinoInput);

        // Verifica que el Destino guardado no sea nulo.
        assertNotNull(saved);
        verify(destinoRepository, times(1)).save(destinoInput);
    }

    @Test
    public void testBuscarPorId() {
        Long id = 1L;
        Destino destino = new Destino();

        when(destinoRepository.findById(id)).thenReturn(Optional.of(destino));

        Destino found = destinoService.getDestinoById(id);

        // Verifica que el Destino encontrado no sea nulo.
        assertNotNull(found);
        verify(destinoRepository, times(1)).findById(id);
    }

    @Test
    public void testActualizar() {
        Destino destino = new Destino();

        when(destinoRepository.save(destino)).thenReturn(destino);

        Destino updated = destinoService.updateDestino(destino);

        // Verifica que el Destino actualizado no sea nulo.
        assertNotNull(updated);
        verify(destinoRepository, times(1)).save(destino);
    }

    @Test
    public void testEliminar() {
        Long id = 1L;

        destinoService.deleteDestino(id);

        // Verifica que deleteById se haya ejecutado una vez.
        verify(destinoRepository, times(1)).deleteById(id);
    }

    @Test
    public void testBuscarPorComuna() {
        Destino destino = new Destino();

        when(destinoRepository.findByComuna("Santiago"))
                .thenReturn(List.of(destino));

        List<Destino> destinos = destinoService.getDestinoByComuna("Santiago");

        // Verifica que la lista no sea nula y tenga un elemento.
        assertNotNull(destinos);
        assertEquals(1, destinos.size());
        verify(destinoRepository, times(1)).findByComuna("Santiago");
    }

    @Test
    public void testBuscarPorCiudad() {
        Destino destino = new Destino();

        when(destinoRepository.findByCiudad("Valparaiso"))
                .thenReturn(List.of(destino));

        List<Destino> destinos = destinoService.getDestinoByCiudad("Valparaiso");

        // Verifica que la lista no sea nula y tenga un elemento.
        assertNotNull(destinos);
        assertEquals(1, destinos.size());
        verify(destinoRepository, times(1)).findByCiudad("Valparaiso");
    }
}

