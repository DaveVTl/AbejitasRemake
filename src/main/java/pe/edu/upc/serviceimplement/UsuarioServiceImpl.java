package pe.edu.upc.serviceimplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Usuario;
import pe.edu.upc.repositories.UserRepository;
import pe.edu.upc.serviceinterface.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

	@Autowired
	private BCryptPasswordEncoder passCryp;
	
	@Autowired
	private UserRepository uR;
	
	@Override
	public Usuario findByUsername(String username) {
		// TODO Auto-generated method stub
		return uR.findByUsername(username);
	}

	@Override
	public Usuario register(Usuario u) {
		u.setPassword(passCryp.encode(u.getPassword()));
		return uR.save(u);
	}
	
	
	
}
