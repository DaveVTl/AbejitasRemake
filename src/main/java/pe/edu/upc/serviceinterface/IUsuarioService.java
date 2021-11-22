package pe.edu.upc.serviceinterface;


import java.util.List;
import java.util.Optional;


import pe.edu.upc.entities.Usuario;



public interface IUsuarioService {
	
	public List<Usuario> list() ;
	Optional <Usuario>findById(int idUser);
	public Integer insert_mype(Usuario u);
	public Integer insert_freelancer(Usuario u);
	public Usuario findByUsername(String username);

}
