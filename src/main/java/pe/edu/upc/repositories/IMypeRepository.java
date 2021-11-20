package pe.edu.upc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Mype;

@Repository
public interface IMypeRepository extends JpaRepository<Mype, Integer>{
	
	@Query("select count (m.rucMype) from Mype m where m.rucMype=:ruc")
	public int FindMypesExists(@Param("ruc") String rucMype);

	@Query("select m from Mype m where m.nameEmpresaMype like %:name%")
	List<Mype> findByName(String name);

	List<Mype> findByNameEmpresaMypeIgnoreCase(String name);
}
