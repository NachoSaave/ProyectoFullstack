package com.example.envio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
@ActiveProfiles("test")
@SpringBootTest
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
        Envio envio = new Envio();

        when(envioRepository.save(envio)).thenReturn(envio);

        Envio saved = envioService.save(envio);

        assertNotNull(saved);
        verify(envioRepository, times(1)).save(envio);
    }

    @Test
    public void testListar() {
        Envio envio = new Envio();

        when(envioRepository.findAll()).thenReturn(List.of(envio));

        List<Envio> envios = envioService.findAll();

        assertNotNull(envios);
        assertEquals(1, envios.size());
    }

    @Test
    public void testBuscarPorId() {
        Long id = 1L;
        Envio envio = new Envio();

        when(envioRepository.findById(id)).thenReturn(Optional.of(envio));

        Envio found = envioService.findById(id);    

        assertNotNull(found);
        verify(envioRepository, times(1)).findById(id);
    }

    @Test
    public void testActualizar() {
        Envio envio = new Envio();

        when(envioRepository.save(envio)).thenReturn(envio);

        Envio updated = envioService.update(envio);

        assertNotNull(updated);
        verify(envioRepository, times(1)).save(envio);
    }

    @Test
    public void testEliminar() {
        Long id = 1L;

        envioService.deleteById(id);

        verify(envioRepository, times(1)).deleteById(id);
    }

    @Test
    public void testObtenerEnvioConDetalles() {
        Long id = 1L;

        Envio envio = new Envio();
        envio.setIdDestino(1L);
        envio.setIdTransporte(1L);

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

