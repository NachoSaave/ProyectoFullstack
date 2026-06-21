package com.example.factura;

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

import com.example.factura.Client.ClienteFeignClient;
import com.example.factura.Model.Factura;
import com.example.factura.Model.DTO.ClienteDTO;
import com.example.factura.Repository.FacturaRepository;
import com.example.factura.Service.FacturaService;
@SpringBootTest
public class FacturaServiceTest {
 @Autowired
    private FacturaService facturaService;

    @MockBean
    private FacturaRepository repo;

    @MockBean
    private ClienteFeignClient client;

    @Test
    public void testListar() {
        Factura factura = new Factura();

        when(repo.findAll()).thenReturn(List.of(factura));

        List<Factura> facturas = facturaService.listar();

        assertNotNull(facturas);
        assertEquals(1, facturas.size());
    }

    @Test
    public void testGuardar() {
        Factura factura = new Factura();

        when(repo.save(factura)).thenReturn(factura);

        Factura guardada = facturaService.guardar(factura);

        assertNotNull(guardada);
        verify(repo, times(1)).save(factura);
    }

    @Test
    public void testBuscarId() {
        Long id = 1L;
        Factura factura = new Factura();

        when(repo.findById(id)).thenReturn(Optional.of(factura));

        Factura encontrada = facturaService.buscarId(id);

        assertNotNull(encontrada);
        verify(repo, times(1)).findById(id);
    }

    @Test
    public void testEliminar() {
        Long id = 1L;

        facturaService.eliminar(id);

        verify(repo, times(1)).deleteById(id);
    }

    @Test
    public void testObtenerFacturaYCliente() {
        Long id = 1L;

        Factura factura = new Factura();
        factura.setIdCliente(10L);

        ClienteDTO cliente = new ClienteDTO();

        when(repo.findById(id)).thenReturn(Optional.of(factura));
        when(client.getClienteById(10L)).thenReturn(cliente);

        Map<String, Object> respuesta = facturaService.obtenerFacturaYCliente(id);

        assertNotNull(respuesta);
        assertEquals(factura, respuesta.get("factura"));
        assertEquals(cliente, respuesta.get("cliente"));
    }
}

