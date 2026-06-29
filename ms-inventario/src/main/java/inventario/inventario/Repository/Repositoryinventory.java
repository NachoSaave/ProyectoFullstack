package inventario.inventario.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Repositoryinventory extends JpaRepository<inventario, Long> {
Optional<inventario> findByIdMoto(Long idMoto);
}
