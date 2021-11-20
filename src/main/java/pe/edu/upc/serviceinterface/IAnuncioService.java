package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Anuncio;

public interface IAnuncioService {
	public Integer insert(Anuncio anuncio);

	List<Anuncio> list();
	
	Anuncio listarId(int id);
	
	Optional<Anuncio> findById(int idAnuncio);

	public void delete(int id);
	
}
