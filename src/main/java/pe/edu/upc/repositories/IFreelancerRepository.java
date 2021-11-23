package pe.edu.upc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Freelancers;
import pe.edu.upc.entities.Usuario;


@Repository
public interface IFreelancerRepository extends JpaRepository<Freelancers, Integer> {

	@Query("select count (f.dniFreelancers) from Freelancers f where f.dniFreelancers=:dni")
	public int FindFreelancersExists(@Param("dni") String dni);

	@Query("select f from Freelancers f where f.nameFreelancers like %:name%")
	List<Freelancers> findByName(String name);

	List<Freelancers> findByNameFreelancersIgnoreCase(String name);
	
<<<<<<< HEAD
	public Freelancers findByUsuarioUsername(String username);
=======
	public Freelancers findByUsuario(Usuario usuario);
	
	@Query( value="SELECT f.name_freelancers,AVG(r.id_score)"
			+ "from trabajo t join reviews r on t.id_trabajo = r.id_trabajo "
			+ "join freelancer f on t.id_freelancers = f.id_freelancers "
			+ "group by f.name_freelancers ",
			nativeQuery = true )
	public List<String[]> freelancerXord();
>>>>>>> 003f12c74fb4ccac4aa319d2f785dbb4d7d30086
}
