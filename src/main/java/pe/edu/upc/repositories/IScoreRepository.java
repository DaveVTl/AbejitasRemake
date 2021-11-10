package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Score;

@Repository
public interface IScoreRepository extends  JpaRepository<Score,Integer>{
	@Query("select count (r.number) from Score r where r.number=:name")	
	public int FindScoreExists(@Param("name") int name);
}
