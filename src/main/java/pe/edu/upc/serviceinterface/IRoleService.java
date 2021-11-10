package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entities.Rol;

public interface IRoleService {
	public void insert(Rol role);

	List<Rol> list();

}
