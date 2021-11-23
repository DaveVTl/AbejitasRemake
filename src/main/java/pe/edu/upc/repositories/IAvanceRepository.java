package pe.edu.upc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Avances;
import pe.edu.upc.entities.Trabajo;

@Repository
public interface IAvanceRepository extends JpaRepository<Avances,Integer>{
	@Query("select count (a.nombre) from Avances a where a.nombre=:name")	
	public int FindAvanceExists(@Param("name") String name);
	
	@Query("select a from Avances a where a.trabajo.idTrabajo=:idtrabajo")
	List<Avances> FindByTrabajo(int idtrabajo);
}
