package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entities.Mype;

public interface IMypeService {
	public Integer insert(Mype mype);

	List<Mype> list();
}
