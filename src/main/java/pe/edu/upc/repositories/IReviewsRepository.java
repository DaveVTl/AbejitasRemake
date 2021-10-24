package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Reviews;
import pe.edu.upc.entities.Trabajo;

@Repository
public interface IReviewsRepository extends  JpaRepository<Reviews,Integer> {
	@Query("select count (r.idTrabajo) from Reviews r where r.idTrabajo=:name")	
	public int FindReviewExists(@Param("name") Trabajo name);
}
