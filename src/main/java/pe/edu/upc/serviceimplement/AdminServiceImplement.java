package pe.edu.upc.serviceimplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Admin;
import pe.edu.upc.repositories.IAdminRepository;
import pe.edu.upc.serviceinterface.IAdminService;

@Service
public class AdminServiceImplement implements IAdminService{

	@Autowired
	private IAdminRepository aR;
	
	@Override
	public Integer insert(Admin admin) {
		int rpta=aR.FindMypeExists(admin.getDNI());
		if (rpta==0) {
			aR.save(admin);
		}
		return rpta;
	}

	@Override
	public List<Admin> list() {
		// TODO Auto-generated method stub
		return aR.findAll();
	}
	

}
