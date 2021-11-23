package pe.edu.upc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Anuncio;

@Repository
public interface IAnuncioRepository extends JpaRepository<Anuncio, Integer>{
	
	@Query("select count (a.idAnuncio) from Anuncio a where a.idAnuncio=:id")
	public int FindAnuncioExists(int id);

	@Query("select a from Anuncio a where a.nameAnuncio like %:name%")
	List<Anuncio> findByName(String name);

	List<Anuncio> findByNameAnuncioIgnoreCase(String name);

}
