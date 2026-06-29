package inventory.inventory.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import inventory.inventory.Model.inventory;

public interface Repositoryinventory extends JpaRepository<inventory, Long> {
Optional<inventory> findByIdMoto(Long idMoto);
}
