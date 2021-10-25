package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entities.Trabajo;

public interface ITrabajoService {
	
	public boolean insert(Trabajo trabajo);
	
	List<Trabajo> list();
	
	Trabajo listarId(int idTrabajo);
}
