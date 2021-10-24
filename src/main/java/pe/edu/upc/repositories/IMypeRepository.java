package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Mype;

@Repository
public interface IMypeRepository extends JpaRepository<Mype, Integer>{
	
	@Query("select count (m.rucMype) from Mype m where m.rucMype=:name")	
	public int FindMypeExists(@Param("name") String name);
}
