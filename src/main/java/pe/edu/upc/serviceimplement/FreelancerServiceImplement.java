package pe.edu.upc.serviceimplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Freelancers;
import pe.edu.upc.repositories.IFreelancerRepository;
import pe.edu.upc.serviceinterface.IFreelancerService;

@Service
public class FreelancerServiceImplement implements IFreelancerService {

	@Autowired
	private IFreelancerRepository fR;

	@Override
	public Integer insert(Freelancers freelancer) {
		int rpta = fR.FindFreelancersExists(freelancer.getNameFreelancers());
		if (rpta == 0) {
			fR.save(freelancer);
		}
		return rpta;
	}

	@Override
	public List<Freelancers> list() {
		// TODO Auto-generated method stub
		return fR.findAll();
	}

}
