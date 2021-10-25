package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entities.Avances;


public interface IAvanceService {
	public boolean insert(Avances anuncio);

	List<Avances> list();
	
	Avances listarId(int idAvance);
}
