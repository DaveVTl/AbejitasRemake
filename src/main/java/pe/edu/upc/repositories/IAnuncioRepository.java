package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Anuncio;

@Repository
public interface IAnuncioRepository extends JpaRepository<Anuncio, Integer>{
	
	@Query("select count (a.idAnuncio) from Anuncio a where a.idAnuncio=:name")	
	public int FindMypeExists(@Param("name") int i);
}
