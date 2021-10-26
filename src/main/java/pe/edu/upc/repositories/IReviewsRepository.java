package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Reviews;


@Repository
public interface IReviewsRepository extends  JpaRepository<Reviews,Integer> {
	@Query("select count (r.descripcionre) from Reviews r where r.descripcionre=:name")	
	public int FindReviewExists(@Param("name") String name);
}
