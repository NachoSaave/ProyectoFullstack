package com.example.factura;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.example.factura.Client.ClienteFeignClient;
import com.example.factura.Model.Factura;
import com.example.factura.Model.DTO.ClienteDTO;
import com.example.factura.Repository.FacturaRepository;
import com.example.factura.Service.FacturaService;

@SpringBootTest
@ActiveProfiles("test")
public class FacturaServiceTest {

    @Autowired
    private FacturaService facturaService;

    @MockBean
    private FacturaRepository repo;

    @MockBean
    private ClienteFeignClient client;

    @Test
    public void testListar() {

        Factura factura = new Factura(
                1L,
                10L,
                "Factura de transporte"
        );

        when(repo.findAll()).thenReturn(List.of(factura));

        List<Factura> facturas = facturaService.listar();

        assertNotNull(facturas);
        assertEquals(1, facturas.size());
        assertEquals(1L, facturas.get(0).getId());
        assertEquals(10L, facturas.get(0).getIdCliente());
        assertEquals("Factura de transporte", facturas.get(0).getDescripcion());
    }

    @Test
    public void testGuardar() {

        Factura factura = new Factura(
                1L,
                10L,
                "Factura de transporte"
        );

        when(repo.save(factura)).thenReturn(factura);

        Factura guardada = facturaService.guardar(factura);

        assertNotNull(guardada);
        assertEquals(1L, guardada.getId());
        assertEquals(10L, guardada.getIdCliente());
        assertEquals("Factura de transporte", guardada.getDescripcion());

        verify(repo, times(1)).save(factura);
    }

    @Test
    public void testBuscarId() {

        Long id = 1L;

        Factura factura = new Factura(
                id,
                10L,
                "Factura de transporte"
        );

        when(repo.findById(id)).thenReturn(Optional.of(factura));

        Factura encontrada = facturaService.buscarId(id);

        assertNotNull(encontrada);
        assertEquals(id, encontrada.getId());
        assertEquals(10L, encontrada.getIdCliente());
        assertEquals("Factura de transporte", encontrada.getDescripcion());

        verify(repo, times(1)).findById(id);
    }

    @Test
    public void testEliminar() {

        Long id = 1L;

        doNothing().when(repo).deleteById(id);

        facturaService.eliminar(id);

        verify(repo, times(1)).deleteById(id);
    }

    @Test
    public void testObtenerFacturaYCliente() {

        Long id = 1L;

        Factura factura = new Factura(
                id,
                10L,
                "Factura de transporte"
        );

        ClienteDTO cliente = new ClienteDTO();

        when(repo.findById(id)).thenReturn(Optional.of(factura));
        when(client.getClienteById(10L)).thenReturn(cliente);

        Map<String, Object> respuesta = facturaService.obtenerFacturaYCliente(id);

        assertNotNull(respuesta);
        assertEquals(factura, respuesta.get("factura"));
        assertEquals(cliente, respuesta.get("cliente"));

        verify(repo, times(1)).findById(id);
        verify(client, times(1)).getClienteById(10L);
    }
}