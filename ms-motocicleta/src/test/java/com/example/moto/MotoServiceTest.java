package com.example.moto;

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

import com.example.moto.Model.Moto;
import com.example.moto.Repository.MotoRepository;
import com.example.moto.Service.MotoService;

@SpringBootTest
@ActiveProfiles("test")
class MotoServiceTest {

    @Autowired
    private MotoService service;

    @MockBean
    private MotoRepository repo;

    @Test
    void testGuardar() {

        Moto moto = new Moto(
                1L,
                "Yamaha",
                "MT-07",
                7990000.0,
                2024,
                689
        );

        when(repo.save(moto)).thenReturn(moto);

        Moto resultado = service.guardar(moto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Yamaha", resultado.getMarca());
        assertEquals("MT-07", resultado.getModelo());
        assertEquals(7990000.0, resultado.getPrecio());
        assertEquals(2024, resultado.getAno());
        assertEquals(689, resultado.getCc());

        verify(repo, times(1)).save(moto);
    }

    @Test
    void testBuscarPorId() {

        Long id = 1L;

        Moto moto = new Moto(
                id,
                "Honda",
                "CB500F",
                6890000.0,
                2023,
                471
        );

        when(repo.findById(id)).thenReturn(Optional.of(moto));

        Moto resultado = service.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals("Honda", resultado.getMarca());
        assertEquals("CB500F", resultado.getModelo());
        assertEquals(6890000.0, resultado.getPrecio());
        assertEquals(2023, resultado.getAno());
        assertEquals(471, resultado.getCc());

        verify(repo, times(1)).findById(id);
    }

    @Test
    void testMostrar() {

        Moto moto = new Moto(
                1L,
                "Kawasaki",
                "Z650",
                8490000.0,
                2024,
                649
        );

        when(repo.findAll()).thenReturn(List.of(moto));

        List<Moto> motos = service.mostrar();

        assertNotNull(motos);
        assertEquals(1, motos.size());
        assertEquals("Kawasaki", motos.get(0).getMarca());
        assertEquals("Z650", motos.get(0).getModelo());
        assertEquals(8490000.0, motos.get(0).getPrecio());

        verify(repo, times(1)).findAll();
    }

    @Test
    void testActualizar() {

        Moto moto = new Moto(
                1L,
                "Suzuki",
                "GSX-8S",
                9290000.0,
                2025,
                776
        );

        when(repo.save(moto)).thenReturn(moto);

        Moto resultado = service.actualizar(moto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Suzuki", resultado.getMarca());
        assertEquals("GSX-8S", resultado.getModelo());
        assertEquals(9290000.0, resultado.getPrecio());
        assertEquals(2025, resultado.getAno());
        assertEquals(776, resultado.getCc());

        verify(repo, times(1)).save(moto);
    }
}