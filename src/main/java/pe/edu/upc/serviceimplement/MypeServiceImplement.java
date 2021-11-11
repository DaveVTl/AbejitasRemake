package pe.edu.upc.serviceimplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Override
	@Transactional(readOnly = true)
	public Mype listarId(int idreview) {
		Optional<Mype> op = mR.findById(idreview);
		return op.isPresent() ? op.get() : new Mype();
	}
	
	@Override
	@Transactional
	public void delete(int idreview) {
		mR.deleteById(idreview);
	}
	
}
