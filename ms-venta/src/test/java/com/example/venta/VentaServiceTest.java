package com.example.venta;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
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

@SpringBootTest
@ActiveProfiles("test")
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

        venta v = new venta(
                1L,
                1L,
                1L,
                5000000.0,
                "PAGADA",
                LocalDate.of(2026, 6, 28)
        );

        when(repository.findAll()).thenReturn(List.of(v));

        List<venta> ventas = service.listar();

        assertNotNull(ventas);
        assertEquals(1, ventas.size());
        assertEquals(1L, ventas.get(0).getId());
        assertEquals(5000000.0, ventas.get(0).getTotal());
        assertEquals("PAGADA", ventas.get(0).getEstado());

        verify(repository, times(1)).findAll();
    }

    @Test
    void testBuscarPorId() {

        Long id = 1L;

        venta v = new venta(
                id,
                1L,
                1L,
                5000000.0,
                "PAGADA",
                LocalDate.of(2026, 6, 28)
        );

        when(repository.findById(id)).thenReturn(Optional.of(v));

        venta resultado = service.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals(5000000.0, resultado.getTotal());
        assertEquals("PAGADA", resultado.getEstado());

        verify(repository, times(1)).findById(id);
    }

    @Test
    void testCrearVenta() {

        VentaSolicitudDTO dto = new VentaSolicitudDTO();
        dto.setIdCliente(1L);
        dto.setIdMoto(1L);

        ClienteDTO cliente = new ClienteDTO();
        cliente.setId(1L);
        cliente.setNombre("Matías");
        cliente.setTelefono("987654321");

        MotoDto moto = new MotoDto();
        moto.setId(1L);
        moto.setPrecio(5000000.0);

        InventarioDTO inventario = new InventarioDTO();
        inventario.setIdMoto(1L);
        inventario.setStock(5);

        PagoDTO pago = new PagoDTO();
        pago.setEstado("APROBADO");

        venta ventaGuardada = new venta(
                1L,
                1L,
                1L,
                5000000.0,
                "PAGADO",
                LocalDate.now()
        );

        when(clienteClient.obtenerCliente(1L)).thenReturn(cliente);
        when(motoClient.obtenerMoto(1L)).thenReturn(moto);
        when(inventarioClient.obtenerInventario(1L)).thenReturn(inventario);

        // el servicio guarda DOS veces, por eso devolvemos el mismo objeto en ambas llamadas
        when(repository.save(any(venta.class))).thenReturn(ventaGuardada);

        when(pagoClient.procesar(any(PagoDTO.class))).thenReturn(pago);

        venta resultado = service.crearVenta(dto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals(1L, resultado.getIdCliente());
        assertEquals(1L, resultado.getIdMoto());
        assertEquals(5000000.0, resultado.getTotal());
        assertEquals("PAGADO", resultado.getEstado());

        verify(clienteClient, times(1)).obtenerCliente(1L);
        verify(motoClient, times(1)).obtenerMoto(1L);
        verify(inventarioClient, times(1)).obtenerInventario(1L);
        verify(inventarioClient, times(1))
                .actualizarInventario(eq(1L), any(InventarioDTO.class));
        verify(pagoClient, times(1)).procesar(any(PagoDTO.class));

        // el servicio hace DOS saves
        verify(repository, times(2)).save(any(venta.class));
    }
}