package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Freelancers;

@Repository
public interface IFreelancerRepository extends JpaRepository<Freelancers, Integer> {

	@Query("select count (f.dniFreelancers) from Freelancers f where f.dniFreelancers=:dni")
	public int FindFreelancersExists(@Param("dni") String dni);

}
