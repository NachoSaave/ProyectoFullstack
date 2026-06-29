package inventory.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import inventory.inventory.Model.inventory;
import inventory.inventory.Repository.Repositoryinventory;
import inventory.inventory.Service.InventarioService;

@SpringBootTest
@ActiveProfiles("test")
public class InventarioServiceTest {

    @Autowired
    private InventarioService inventarioService;

    @MockBean
    private Repositoryinventory repository;

    @Test
    public void testListar() {

        inventory inv = new inventory();
        inv.setId(1L);
        inv.setIdMoto(100L);
        inv.setStock(15);

        when(repository.findAll()).thenReturn(List.of(inv));

        List<inventory> lista = inventarioService.listar();

        assertNotNull(lista);
        assertEquals(1, lista.size());
        assertEquals(1L, lista.get(0).getId());
        assertEquals(100L, lista.get(0).getIdMoto());
        assertEquals(15, lista.get(0).getStock());
    }

    @Test
    public void testGuardar() {

        inventory inv = new inventory();
        inv.setId(1L);
        inv.setIdMoto(100L);
        inv.setStock(15);

        when(repository.save(inv)).thenReturn(inv);

        inventory guardado = inventarioService.guardar(inv);

        assertNotNull(guardado);
        assertEquals(1L, guardado.getId());
        assertEquals(100L, guardado.getIdMoto());
        assertEquals(15, guardado.getStock());

        verify(repository, times(1)).save(inv);
    }

    @Test
    public void testBuscarPorIdMoto() {

        Long idMoto = 100L;

        inventory inv = new inventory();
        inv.setId(1L);
        inv.setIdMoto(idMoto);
        inv.setStock(15);

        when(repository.findByIdMoto(idMoto)).thenReturn(Optional.of(inv));

        inventory encontrado = inventarioService.buscarPorIdMoto(idMoto);

        assertNotNull(encontrado);
        assertEquals(1L, encontrado.getId());
        assertEquals(idMoto, encontrado.getIdMoto());
        assertEquals(15, encontrado.getStock());

        verify(repository, times(1)).findByIdMoto(idMoto);
    }

    @Test
    public void testEliminar() {

        Long idMoto = 100L;

        inventory inv = new inventory();
        inv.setId(1L);
        inv.setIdMoto(idMoto);
        inv.setStock(15);

        when(repository.findByIdMoto(idMoto)).thenReturn(Optional.of(inv));

        inventarioService.eliminar(idMoto);

        verify(repository, times(1)).findByIdMoto(idMoto);
        verify(repository, times(1)).deleteById(1L);
    }
}