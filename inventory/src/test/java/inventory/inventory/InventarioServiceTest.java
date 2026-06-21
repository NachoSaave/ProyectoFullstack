package inventory.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import inventory.inventory.Model.inventory;
import inventory.inventory.Repository.Repositoryinventory;
import inventory.inventory.Service.InventarioService;

@SpringBootTest
public class InventarioServiceTest {

    @Autowired
    private InventarioService inventarioService;

    @MockBean
    private Repositoryinventory repository;

    @Test
    public void testListar() {
        inventory inv = new inventory();

        when(repository.findAll()).thenReturn(List.of(inv));

        List<inventory> lista = inventarioService.listar();

        assertNotNull(lista);
        assertEquals(1, lista.size());
    }

    @Test
    public void testGuardar() {
        inventory inv = new inventory();

        when(repository.save(inv)).thenReturn(inv);

        inventory guardado = inventarioService.guardar(inv);

        assertNotNull(guardado);
        verify(repository, times(1)).save(inv);
    }

    @Test
    public void testBuscarPorIdMoto() {
        Long idMoto = 1L;
        inventory inv = new inventory();

        when(repository.findByIdMoto(idMoto)).thenReturn(Optional.of(inv));

        inventory encontrado = inventarioService.buscarPorIdMoto(idMoto);

        assertNotNull(encontrado);
        verify(repository, times(1)).findByIdMoto(idMoto);
    }

    @Test
    public void testEliminar() {
        Long idMoto = 1L;

        inventory inv = new inventory();
        inv.setId(10L);

        when(repository.findByIdMoto(idMoto)).thenReturn(Optional.of(inv));

        inventarioService.eliminar(idMoto);

        verify(repository, times(1)).findByIdMoto(idMoto);
        verify(repository, times(1)).deleteById(10L);
    }
}