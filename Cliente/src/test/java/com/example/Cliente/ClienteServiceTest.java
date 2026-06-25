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


        assertNotNull(clientes);
        assertEquals(1, clientes.size());
    }

    @Test
    public void testGuardar() {
        Cliente clienteInput = new Cliente();
        
        when(clienteRepository.save(clienteInput)).thenReturn(clienteInput);

        Cliente saved = clienteService.guardar(clienteInput);
        assertNotNull(saved);
        verify(clienteRepository, times(1)).save(clienteInput);
    }

    @Test
    public void testBuscarPorId() {
        Long id = 1L;
        Cliente cliente = new Cliente();
        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        Cliente found = clienteService.buscarPorId(id);
        assertNotNull(found);
        verify(clienteRepository, times(1)).findById(id);
    }
}