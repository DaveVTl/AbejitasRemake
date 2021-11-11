package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;


import pe.edu.upc.entities.Freelancers;


public interface IFreelancerService {
	
	public Integer insert(Freelancers freelancer);

	List<Freelancers> list();
	
	Optional<Freelancers> listarId(int id);
	
	Optional <Freelancers>findById(int idCustomer);
	
	List<Freelancers> findByName(String nameFreelancers);
	
	List<Freelancers> findByNameFreelancersIgnoreCase(String nameFreelancers);



	
	
}
