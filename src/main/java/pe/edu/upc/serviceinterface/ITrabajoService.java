package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Trabajo;


public interface ITrabajoService {

	public boolean insert(Trabajo trabajo);

	List<Trabajo> list();

	Trabajo listarId(int idTrabajo);
	Optional<Trabajo> findById(int idTrabajo);
}