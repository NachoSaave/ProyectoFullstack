package com.example.Cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import com.example.Cliente.Model.Cliente;
import com.example.Cliente.Repository.ClienteRepository;
import com.example.Cliente.Service.ClienteService;
import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
@ActiveProfiles("test")
@SpringBootTest
public class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

    @MockBean
    private ClienteRepository clienteRepository;

    @Test
    public void testListar() {
        Cliente cliente = new Cliente(); 
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));
        List<Cliente> clientes = clienteService.listar();

        // Verifica que la lista devuelta no sea nula y contenga exactamente un Cliente.
        assertNotNull(clientes);
        assertEquals(1, clientes.size());
    }

    @Test
    public void testGuardar() {
        Cliente clienteInput = new Cliente();
        
        when(clienteRepository.save(clienteInput)).thenReturn(clienteInput);

        Cliente saved = clienteService.guardar(clienteInput);

        // Verifica que el Cliente guardado no sea nulo.
        assertNotNull(saved);
        verify(clienteRepository, times(1)).save(clienteInput);
    }

    @Test
    public void testBuscarPorId() {
        Long id = 1L;
        Cliente cliente = new Cliente();
        // Define el comportamiento del mock: cuando se llame a findById() con el ID, devuelve un Cliente opcional.
        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        Cliente found = clienteService.buscarPorId(id);

        // Verifica que el Cliente devuelto no sea nulo.
        assertNotNull(found);
        verify(clienteRepository, times(1)).findById(id);
    }
}

