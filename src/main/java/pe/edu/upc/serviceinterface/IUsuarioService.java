package pe.edu.upc.serviceinterface;


import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Usuario;

@Service
public interface IUsuarioService {
	
	public Usuario findByUsername(String username);
	
	public Usuario register(Usuario u);
	
}
