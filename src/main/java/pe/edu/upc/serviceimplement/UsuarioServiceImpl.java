package pe.edu.upc.serviceimplement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Rol;
import pe.edu.upc.entities.Usuario;
import pe.edu.upc.repositories.RoleRepository;
import pe.edu.upc.repositories.UserRepository;
import pe.edu.upc.serviceinterface.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

	@Autowired
	private UserRepository uR;
	
	
	
	@Override
	public Integer insert_mype(Usuario u) {
		int rpta = uR.buscarUsername(u.getUsername());
		if (rpta == 0) {
			
			Rol role = new Rol();
			role.setType("ROLE_MYPE");
			List<Rol> roles = new ArrayList<Rol>();
			roles.add(role);
			u.setRoles(roles);
			uR.save(u);
			
		}
		return rpta;
	}
	
	@Override
	public Integer insert_freelancer(Usuario u) {
		int rpta = uR.buscarUsername(u.getUsername());
		if (rpta == 0) {
			
			Rol role = new Rol();
			role.setType("ROLE_FREELANCER");
			List<Rol> roles = new ArrayList<Rol>();
			roles.add(role);
			u.setRoles(roles);
			uR.save(u);
			
		}
		return rpta;
	}
	
	

	@Override
	public List<Usuario> list() {
		// TODO Auto-generated method stub
		return uR.findAll();
	}
	
}
