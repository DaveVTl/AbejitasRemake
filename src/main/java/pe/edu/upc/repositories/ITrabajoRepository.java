package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Trabajo;

@Repository
public interface ITrabajoRepository extends JpaRepository<Trabajo,Integer>{

	@Query("select count (t.EstadoTrabajo) from Trabajo t where c.EstadoTrabajo=:status")	
	public int FindJobByStatus(@Param("status") String status);
	
}
