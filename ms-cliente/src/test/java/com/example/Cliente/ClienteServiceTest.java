package com.example.Cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import com.example.Cliente.Model.Cliente;
import com.example.Cliente.Repository.ClienteRepository;
import com.example.Cliente.Service.ClienteService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

    @MockBean
    private ClienteRepository clienteRepository;

    @Test
    public void testListar() {

        // datos de prueba
        Cliente cliente = new Cliente(1L, "Juan Pérez", "987654321");

        // simula el comportamiento del repositorio
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        //llama al servicio
        List<Cliente> clientes = clienteService.listar();

        // verificaciones
        assertNotNull(clientes);
        assertEquals(1, clientes.size());
        assertEquals("Juan Pérez", clientes.get(0).getNombre());
        assertEquals("987654321", clientes.get(0).getTelefono());
    }

    @Test
    public void testGuardar() {

        Cliente cliente = new Cliente(1L, "María González", "912345678");

        // simula el guardado
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente saved = clienteService.guardar(cliente);

        // verificaciones
        assertNotNull(saved);
        assertEquals(1L, saved.getId());
        assertEquals("María González", saved.getNombre());
        assertEquals("912345678", saved.getTelefono());

        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    public void testBuscarPorId() {

        Long id = 1L;

        Cliente cliente = new Cliente(id, "Carlos Soto", "998877665");

        // simula la búsqueda
        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        // llama al servicio
        Cliente found = clienteService.buscarPorId(id);

        // verificaciones
        assertNotNull(found);
        assertEquals(id, found.getId());
        assertEquals("Carlos Soto", found.getNombre());
        assertEquals("998877665", found.getTelefono());

        verify(clienteRepository, times(1)).findById(id);
    }
}