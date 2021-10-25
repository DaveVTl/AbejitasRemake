package pe.edu.upc.serviceimplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Anuncio;
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
	

}
