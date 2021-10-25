package pe.edu.upc.serviceimplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Avances;
import pe.edu.upc.repositories.IAvanceRepository;
import pe.edu.upc.serviceinterface.IAvanceService;

@Service
public class AvanceServiceImplement implements IAvanceService{
	@Autowired
	private IAvanceRepository aR;
	
	@Override
	public Integer insert(Avances avance) {
		int rpta=aR.FindAvanceExists(avance.getNombre());
		if (rpta==0) {
			aR.save(avance);
		}
		return rpta;
	}

	@Override
	public List<Avances> list() {
		// TODO Auto-generated method stub
		return aR.findAll();
	}
}
