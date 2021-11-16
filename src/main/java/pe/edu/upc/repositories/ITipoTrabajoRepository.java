package pe.edu.upc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.TipoTrabajo;

@Repository
public interface ITipoTrabajoRepository extends JpaRepository<TipoTrabajo, Integer> {
	@Query("select count(t.nombreTrabajo) from TipoTrabajo t where t.nombreTrabajo=:nombre")
	public int TrabajoExistente(@Param("nombre") String nombre);

	@Query( value="SELECT t.nombre_trabajo,count(a.id_anuncio)"
			+ "from anuncio a join tipo_trabajo t on a.id_tipo_trabajo= t.id_tipo_trabajo "
			+ "group by t.id_tipo_trabajo ",
			nativeQuery = true )
	public List<String[]> ttrabajoXord();
}
