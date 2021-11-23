package pe.edu.upc.serviceimplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Anuncio;
import pe.edu.upc.entities.Mype;
import pe.edu.upc.repositories.IAnuncioRepository;
import pe.edu.upc.serviceinterface.IAnuncioService;

@Service
public class AnuncioServiceImplement implements IAnuncioService{

	@Autowired
	private IAnuncioRepository aR;
	
	@Override
	@Transactional
	public Integer insert(Anuncio anuncio) {
		
		int rpta = aR.FindAnuncioExists(anuncio.getIdAnuncio());
		if (rpta == 0) {
			aR.save(anuncio);
		}
		return rpta;
	}

	@Override
	public List<Anuncio> list() {
		// TODO Auto-generated method stub
		return aR.findAll(Sort.by(Sort.Direction.DESC, "nameAnuncio"));
	}
	
	@Override
	public Optional<Anuncio> findById(int idAnuncio) {
		// TODO Auto-generated method stub
		return aR.findById(idAnuncio);
	}

	@Override
	public List<Anuncio> findByName(String nameAnuncio) {
		// TODO Auto-generated method stub
		return aR.findByName(nameAnuncio);
	}

	
	@Override
	public List<Anuncio> findByNameAnuncioIgnoreCase(String nameAnuncio){
		return aR.findByNameAnuncioIgnoreCase(nameAnuncio);
	}
	
	@Override
	public Optional<Anuncio> listarId(int id) {
		// TODO Auto-generated method stub
		return aR.findById(id);
	}
	
	@Override
	@Transactional
	public void delete(int idAnuncio) {
		aR.deleteById(idAnuncio);
	}

	@Override
	public List<Anuncio> fetchAnuncioByName(String nameAnuncio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Anuncio> fetchAnuncioByTrabajoName(String nameTrabajo) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
