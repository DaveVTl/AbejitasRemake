package pe.edu.upc.serviceimplement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Freelancers;
import pe.edu.upc.entities.Rol;
import pe.edu.upc.entities.Usuario;
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
			Rol role = new Rol();
			role.setType("ROLE_FREELANCER");
			List<Rol> roles = new ArrayList<Rol>();
			roles.add(role);
			freelancer.getUsuario().setRoles(roles);
			fR.save(freelancer);
		}
		return rpta;
	}

	@Override
	public List<Freelancers> list() {
		// TODO Auto-generated method stub
		return fR.findAll(Sort.by(Sort.Direction.DESC, "nameFreelancers"));
	}
	
	@Override
	public Optional<Freelancers> findById(int idCustomer) {
		// TODO Auto-generated method stub
		return fR.findById(idCustomer);
	}

	@Override
	public List<Freelancers> findByName(String nameFreelancers) {
		// TODO Auto-generated method stub
		return fR.findByName(nameFreelancers);
	}

	
	@Override
	public List<Freelancers> findByNameFreelancersIgnoreCase(String nameFreelancers){
		return fR.findByNameFreelancersIgnoreCase(nameFreelancers);
	}
	
	@Override
	public Optional<Freelancers> listarId(int id) {
		// TODO Auto-generated method stub
		return fR.findById(id);
	}

	@Override
	public Freelancers findByUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return fR.findByUsuario(usuario);
	}

	@Override
	public List<String[]> freelancerXord() {
		// TODO Auto-generated method stub
		return fR.freelancerXord();
	}

}
