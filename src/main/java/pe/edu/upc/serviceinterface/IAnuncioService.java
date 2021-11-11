package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entities.Anuncio;
import pe.edu.upc.entities.Mype;

public interface IAnuncioService {
	public Integer insert(Anuncio anuncio);

	List<Anuncio> list();
	
	Anuncio listarId(int id);

	public void delete(int id);
	
}
