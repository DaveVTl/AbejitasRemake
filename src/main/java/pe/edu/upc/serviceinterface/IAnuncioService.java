package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Anuncio;
import pe.edu.upc.entities.Mype;



public interface IAnuncioService {

	public Integer insert(Anuncio anuncio);

	List<Anuncio> list();
	
	Optional<Anuncio> listarId(int idAnuncio);
	
	Optional <Anuncio>findById(int idAnuncio);
	
	List<Anuncio> findByName(String nameAnuncio);

	public void delete(int idAnuncio);
	
	
	
	
	
}
