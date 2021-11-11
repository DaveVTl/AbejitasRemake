package pe.edu.upc.serviceimplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Freelancers;
import pe.edu.upc.repositories.IFreelancerRepository;
import pe.edu.upc.serviceinterface.IFreelancerService;

@Service
public class FreelancerServiceImplement implements IFreelancerService {

	@Autowired
	private IFreelancerRepository fR;

	@Override
	@Transactional
	public Integer insert(Freelancers freelancer) {
		
		int rpta = fR.FindFreelancersExists(freelancer.getDniFreelancers());
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
	
	@Override
	public Optional<Freelancers> findById(int idCustomer) {
		// TODO Auto-generated method stub
		return fR.findById(idCustomer);
	}

}
