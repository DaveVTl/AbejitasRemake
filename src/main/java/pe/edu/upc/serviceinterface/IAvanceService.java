package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entities.Avances;
import pe.edu.upc.entities.Trabajo;


public interface IAvanceService {
	public boolean insert(Avances anuncio);

	List<Avances> list();
	
	Avances listarId(int idAvance);
	
	public void delete(int idAvance);
	
	List<Avances> FindByTrabajo(int trabajo);
}
