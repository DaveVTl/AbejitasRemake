package pe.edu.upc.serviceimplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Avances;
import pe.edu.upc.repositories.IAvanceRepository;
import pe.edu.upc.serviceinterface.IAvanceService;

@Service
public class AvanceServiceImplement implements IAvanceService{
	@Autowired
	private IAvanceRepository aR;
	
	@Override
	public boolean insert(Avances avance) {
		Avances objavance = aR.save(avance);
		if (objavance == null) {
			return false;
		} else {
			return true;
		}
		}

	@Override
	public List<Avances> list() {
		// TODO Auto-generated method stub
		return aR.findAll();
	}
	@Override
	@Transactional(readOnly = true)
	public Avances listarId(int idAvance) {
		Optional<Avances> op = aR.findById(idAvance);
		return op.isPresent() ? op.get() : new Avances();
	}
	
	@Override
	@Transactional
	public void delete(int idAvance) {
		aR.deleteById(idAvance);
	}
}
