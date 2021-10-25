package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entities.Admin;

public interface IAdminService {
	public Integer insert(Admin admin);

	List<Admin> list();
}
