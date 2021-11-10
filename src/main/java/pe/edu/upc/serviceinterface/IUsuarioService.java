package pe.edu.upc.serviceinterface;


import java.util.List;

import pe.edu.upc.entities.Usuario;



public interface IUsuarioService {
	
	public List<Usuario> list() ;

	public Integer insert(Usuario u);

}
