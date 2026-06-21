package com.example.venta;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.example.venta.Client.ClienteFeignClient;
import com.example.venta.Client.InventarioFeingClient;
import com.example.venta.Client.MotoFeingClient;
import com.example.venta.Client.PagoFeingClient;
import com.example.venta.Model.Dto.ClienteDTO;
import com.example.venta.Model.Dto.InventarioDTO;
import com.example.venta.Model.Dto.MotoDto;
import com.example.venta.Model.Dto.PagoDTO;
import com.example.venta.Model.Dto.VentaSolicitudDTO;
import com.example.venta.Model.venta;
import com.example.venta.Repository.ventaRepository;
import com.example.venta.Service.ventaService;
@ActiveProfiles("test")
@SpringBootTest
public class VentaServiceTest {

    @Autowired
    private ventaService service;

    @MockBean
    private ventaRepository repository;

    @MockBean
    private MotoFeingClient motoClient;

    @MockBean
    private InventarioFeingClient inventarioClient;

    @MockBean
    private PagoFeingClient pagoClient;

    @MockBean
    private ClienteFeignClient clienteClient;

    @Test
    void testListar() {

        when(repository.findAll()).thenReturn(List.of(new venta()));

        assertEquals(1, service.listar().size());
    }

    @Test
    void testBuscarPorId() {

        venta v = new venta();

        when(repository.findById(1L)).thenReturn(Optional.of(v));

        assertNotNull(service.buscarPorId(1L));
    }

    @Test
    void testCrearVenta() {

        VentaSolicitudDTO dto = new VentaSolicitudDTO();
        dto.setIdCliente(1L);
        dto.setIdMoto(1L);

        ClienteDTO cliente = new ClienteDTO();
        cliente.setId(1L);

        MotoDto moto = new MotoDto();
        moto.setPrecio(5000000.0);

        InventarioDTO inventario = new InventarioDTO();
        inventario.setIdMoto(1L);
        inventario.setStock(5);

        PagoDTO pago = new PagoDTO();
        pago.setEstado("APROBADO");

        venta v = new venta();
        v.setId(1L);

        when(clienteClient.obtenerCliente(1L)).thenReturn(cliente);
        when(motoClient.obtenerMoto(1L)).thenReturn(moto);
        when(inventarioClient.obtenerInventario(1L)).thenReturn(inventario);
        when(repository.save(any(venta.class))).thenReturn(v);
        when(pagoClient.procesar(any(PagoDTO.class))).thenReturn(pago);

        venta resultado = service.crearVenta(dto);

        assertNotNull(resultado);
    }
}