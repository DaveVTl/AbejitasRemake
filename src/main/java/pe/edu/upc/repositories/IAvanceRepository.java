package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Avances;

@Repository
public interface IAvanceRepository extends JpaRepository<Avances,Integer>{
	@Query("select count (a.nombre) from Avances a where a.nombre=:name")	
	public int FindAvanceExists(@Param("name") String name);
}
