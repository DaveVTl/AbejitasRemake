package pe.edu.upc.serviceimplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Mype;
import pe.edu.upc.repositories.IMypeRepository;
import pe.edu.upc.serviceinterface.IMypeService;

@Service
public class MypeServiceImplement implements IMypeService{

	@Autowired
	private IMypeRepository mR;
	
	@Override
	public Integer insert(Mype mype) {
		int rpta=mR.FindMypeExists(mype.getRucMype());
		if (rpta==0) {
			mR.save(mype);
		}
		return rpta;
	}

	@Override
	public List<Mype> list() {
		// TODO Auto-generated method stub
		return mR.findAll();
	}
	

}
