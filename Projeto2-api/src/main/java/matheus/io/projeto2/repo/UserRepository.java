package matheus.io.projeto2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import matheus.io.projeto2.model.Usuario;

/* interface do repositorio */

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long>{ 
}
