package pe.edu.upc.serviceimplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.TipoTrabajo;
import pe.edu.upc.repositories.ITipoTrabajoRepository;
import pe.edu.upc.serviceinterface.ITipoTrabajoService;

@Service
public class TipoTrabajoServiceImplement implements ITipoTrabajoService {
	@Autowired
	private ITipoTrabajoRepository tR;

	@Override
	@Transactional
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
	@Override
	@Transactional(readOnly = true)
	public TipoTrabajo listarId(int idtipo) {
		Optional<TipoTrabajo> op = tR.findById(idtipo);
		return op.isPresent() ? op.get() : new TipoTrabajo();
	}
	
	@Override
	@Transactional
	public void delete(int idtipo) {
		tR.deleteById(idtipo);
	}
	@Override
	public List<String[]> ttrabajoXord() {
		// TODO Auto-generated method stub
		return tR.ttrabajoXord();
	}
}
