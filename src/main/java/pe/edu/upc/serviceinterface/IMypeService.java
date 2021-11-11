package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entities.Mype;
import pe.edu.upc.entities.Reviews;

public interface IMypeService {
	public Integer insert(Mype mype);

	List<Mype> list();
	
	Mype listarId(int idreview);

	public void delete(int idreview);
}
