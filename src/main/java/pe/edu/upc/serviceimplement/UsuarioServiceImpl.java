package pe.edu.upc.serviceimplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Usuario;

import pe.edu.upc.repositories.UserRepository;
import pe.edu.upc.serviceinterface.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

	@Autowired
	private UserRepository uR;
	
	@Override
	public Integer insert(Usuario u) {
		int rpta = uR.buscarUsername(u.getUsername());
		if (rpta == 0) {
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
