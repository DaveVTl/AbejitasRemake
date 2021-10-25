package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entities.Anuncio;

public interface IAnuncioService {
	public Integer insert(Anuncio anuncio);

	List<Anuncio> list();
}
