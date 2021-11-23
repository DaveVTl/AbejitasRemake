package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Mype;


public interface IMypeService {

	public Integer insert(Mype mype);

	List<Mype> list();
	
	Optional<Mype> listarId(int id);
	
	Optional <Mype>findById(int idCustomer);
	
	List<Mype> findByName(String nameEmpresaMype);
	
	List<Mype> findByNameEmpresaMypeIgnoreCase(String nameEmpresaMype);
	
	public void delete(int idMype);
	
	public List<String[]> mypeMasAnuncios();
	
}


