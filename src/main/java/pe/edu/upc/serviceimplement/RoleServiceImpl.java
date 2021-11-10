package pe.edu.upc.serviceimplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Rol;
import pe.edu.upc.repositories.RoleRepository;
import pe.edu.upc.serviceinterface.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {
	@Autowired
	private RoleRepository rR;

	@Override
	public void insert(Rol role) {
		rR.save(role);
	}

	@Override
	public List<Rol> list() {
		// TODO Auto-generated method stub
		return rR.findAll();
	}
}
