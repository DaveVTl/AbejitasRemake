package pe.edu.upc.serviceimplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Anuncio;
import pe.edu.upc.entities.Trabajo;
import pe.edu.upc.repositories.IAnuncioRepository;
import pe.edu.upc.serviceinterface.IAnuncioService;

@Service
public class AnuncioServiceImplement implements IAnuncioService{

	@Autowired
	private IAnuncioRepository aR;
	
	@Override
	public Integer insert(Anuncio anuncio) {
		int rpta=aR.FindMypeExists(anuncio.getIdAnuncio());
		if (rpta==0) {
			aR.save(anuncio);
		}
		return rpta;
	}

	@Override
	public List<Anuncio> list() {
		// TODO Auto-generated method stub
		return aR.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Anuncio listarId(int id) {
		Optional<Anuncio> op = aR.findById(id);
		return op.isPresent() ? op.get() : new Anuncio();
	}
	
	@Override
	@Transactional
	public void delete(int idreview) {
		aR.deleteById(idreview);
	}
	
	@Override
	@Transactional
	public Optional<Anuncio> findById(int id) {
		return aR.findById(id);

	}
	
}
