package inventario.inventario.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import inventario.inventario.Model.inventario;

public interface inventarioRepository extends JpaRepository<inventario, Long> {
Optional<inventario> findByIdMoto(Long idMoto);
}
