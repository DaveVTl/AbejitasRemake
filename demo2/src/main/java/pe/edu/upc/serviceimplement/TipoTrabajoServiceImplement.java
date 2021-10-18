package pe.edu.upc.serviceimplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.TipoTrabajo;
import pe.edu.upc.repositories.ITipoTrabajoRepository;
import pe.edu.upc.serviceinterface.ITipoTrabajoService;

@Service
public class TipoTrabajoServiceImplement implements ITipoTrabajoService {
	@Autowired
	private ITipoTrabajoRepository tR;

	@Override
	public Integer insert(TipoTrabajo tipo) {
		int rpta=tR.TrabajoExistente(tipo.getNombreTrabajo());
		if(rpta==0){
			tR.save(tipo);
		}
		return rpta;
	}

	@Override
	public List<TipoTrabajo> list() {
		// TODO Auto-generated method stub
		return tR.findAll();
	}

}
