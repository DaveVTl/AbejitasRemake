package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Freelancers;
import pe.edu.upc.entities.Usuario;


@Repository
public interface UserRepository extends JpaRepository<Usuario, Integer> {
	
	public Usuario findByUsername(String username);

	@Query("select count(u.username) from Usuario u where u.username =:username")
	public int buscarUsername(@Param("username") String nombre);
	
	@Transactional
	@Modifying
	@Query(value = "insert into roles (rol, user_id) VALUES (:rol, :user_id)", nativeQuery = true)
	public void insRol(@Param("rol") String authority, @Param("user_id") Integer user_id);
	
	
	
}