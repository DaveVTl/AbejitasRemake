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
	
	@Query(value="Select a.id_Mype, m.name_empresa_mype, COUNT(a.id_anuncio)"
			+ "from Mypes m join Anuncio a on  m.id_Mype = a.id_Mype "
			+ "group by a.id_Mype, m.name_empresa_mype ",
	nativeQuery = true )
	public List<String[]> mypeMasAnuncios();
	
	@Query(value="Select a.id_Anuncio,m.name_Empresa_Mype,a.pago_Anuncio"
	+ "from anuncio a join mypes m on a.id_Mype=m.id_Mype"
	+ "where pago_anuncio between 250 and 500"
	+ "group by a.id_Anuncio,m.name_Empresa_Mype,a.pago_Anuncio",
	nativeQuery = true )
	public List<String[]> anuncioRango();
}
