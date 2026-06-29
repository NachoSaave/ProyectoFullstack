package com.example.envio;

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

import com.example.envio.Client.DestinoFeignClient;
import com.example.envio.Client.TransporteFeignClient;
import com.example.envio.Model.Envio;
import com.example.envio.Model.DTO.DestinoDTO;
import com.example.envio.Model.DTO.TransporteDTO;
import com.example.envio.Repository.EnvioRepository;
import com.example.envio.Service.EnvioService;

@SpringBootTest
@ActiveProfiles("test")
public class EnvioServiceTest {

    @Autowired
    private EnvioService envioService;

    @MockBean
    private EnvioRepository envioRepository;

    @MockBean
    private DestinoFeignClient destinoFeignClient;

    @MockBean
    private TransporteFeignClient transporteFeignClient;

    @Test
    public void testGuardar() {

        Envio envio = new Envio(
                1L,
                "48 horas",
                "12345678-9",
                1L,
                1L
        );

        when(envioRepository.save(envio)).thenReturn(envio);

        Envio saved = envioService.save(envio);

        assertNotNull(saved);
        assertEquals(1L, saved.getId());
        assertEquals("48 horas", saved.getTiempoEstimado());
        assertEquals("12345678-9", saved.getRutReceptor());
        assertEquals(1L, saved.getIdDestino());
        assertEquals(1L, saved.getIdTransporte());

        verify(envioRepository, times(1)).save(envio);
    }

    @Test
    public void testListar() {

        Envio envio = new Envio(
                1L,
                "48 horas",
                "12345678-9",
                1L,
                1L
        );

        when(envioRepository.findAll()).thenReturn(List.of(envio));

        List<Envio> envios = envioService.findAll();

        assertNotNull(envios);
        assertEquals(1, envios.size());
        assertEquals("48 horas", envios.get(0).getTiempoEstimado());
        assertEquals("12345678-9", envios.get(0).getRutReceptor());
    }

    @Test
    public void testBuscarPorId() {

        Long id = 1L;

        Envio envio = new Envio(
                id,
                "48 horas",
                "12345678-9",
                1L,
                1L
        );

        when(envioRepository.findById(id)).thenReturn(Optional.of(envio));

        Envio found = envioService.findById(id);

        assertNotNull(found);
        assertEquals(id, found.getId());
        assertEquals("48 horas", found.getTiempoEstimado());
        assertEquals("12345678-9", found.getRutReceptor());

        verify(envioRepository, times(1)).findById(id);
    }

    @Test
    public void testActualizar() {

        Envio envio = new Envio(
                1L,
                "24 horas",
                "11111111-1",
                2L,
                2L
        );

        when(envioRepository.save(envio)).thenReturn(envio);

        Envio updated = envioService.update(envio);

        assertNotNull(updated);
        assertEquals("24 horas", updated.getTiempoEstimado());
        assertEquals("11111111-1", updated.getRutReceptor());
        assertEquals(2L, updated.getIdDestino());
        assertEquals(2L, updated.getIdTransporte());

        verify(envioRepository, times(1)).save(envio);
    }

    @Test
    public void testEliminar() {

        Long id = 1L;

        doNothing().when(envioRepository).deleteById(id);

        envioService.deleteById(id);

        verify(envioRepository, times(1)).deleteById(id);
    }

    @Test
    public void testObtenerEnvioConDetalles() {

        Long id = 1L;

        Envio envio = new Envio(
                id,
                "48 horas",
                "12345678-9",
                1L,
                1L
        );

        DestinoDTO destinoDTO = new DestinoDTO();
        TransporteDTO transporteDTO = new TransporteDTO();

        when(envioRepository.findById(id)).thenReturn(Optional.of(envio));
        when(destinoFeignClient.getDestinoById(1L)).thenReturn(destinoDTO);
        when(transporteFeignClient.getTransporteById(1L)).thenReturn(transporteDTO);

        Map<String, Object> respuesta = envioService.obtenerEnvioConDetalles(id);

        assertNotNull(respuesta);
        assertEquals(envio, respuesta.get("envio"));
        assertEquals(destinoDTO, respuesta.get("destino"));
        assertEquals(transporteDTO, respuesta.get("transporte"));

        verify(envioRepository, times(1)).findById(id);
        verify(destinoFeignClient, times(1)).getDestinoById(1L);
        verify(transporteFeignClient, times(1)).getTransporteById(1L);
    }
}