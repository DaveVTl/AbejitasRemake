package pe.edu.upc.serviceinterface;

import java.util.List;


import pe.edu.upc.entities.Freelancers;

public interface IFreelancerService {
	public Integer insert(Freelancers freelancer);

	List<Freelancers> list();
}
