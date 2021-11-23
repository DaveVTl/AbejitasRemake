package pe.edu.upc.serviceimplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Freelancers;
import pe.edu.upc.entities.Mype;
import pe.edu.upc.repositories.IMypeRepository;
import pe.edu.upc.serviceinterface.IMypeService;

@Service
public class MypeServiceImplement implements IMypeService{

	@Autowired
	private IMypeRepository mR;
	
	@Override
	@Transactional
	public Integer insert(Mype mype) {
		
		int rpta = mR.FindMypesExists(mype.getRucMype());
		if (rpta == 0) {
			mR.save(mype);
		}
		return rpta;
	}

	@Override
	public List<Mype> list() {
		// TODO Auto-generated method stub
		return mR.findAll(Sort.by(Sort.Direction.DESC, "nameEmpresaMype"));
	}
	
	@Override
	public Optional<Mype> findById(int idCustomer) {
		// TODO Auto-generated method stub
		return mR.findById(idCustomer);
	}

	@Override
	public List<Mype> findByName(String nameEmpresaMype) {
		// TODO Auto-generated method stub
		return mR.findByName(nameEmpresaMype);
	}

	
	@Override
	public List<Mype> findByNameEmpresaMypeIgnoreCase(String nameEmpresaMype){
		return mR.findByNameEmpresaMypeIgnoreCase(nameEmpresaMype);
	}
	
	@Override
	public Optional<Mype> listarId(int id) {
		// TODO Auto-generated method stub
		return mR.findById(id);
	}
	
	@Override
	@Transactional
	public void delete(int idMype) {
		mR.deleteById(idMype);
	}

	@Override
	public List<String[]> mypeMasAnuncios() {
		// TODO Auto-generated method stub
		return mR.mypeMasAnuncios();
	}
}
