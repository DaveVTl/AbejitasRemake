package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
	public Usuario findByUsername(String username);
}