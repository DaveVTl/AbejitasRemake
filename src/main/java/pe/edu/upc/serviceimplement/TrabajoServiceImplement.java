package pe.edu.upc.serviceimplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Trabajo;
import pe.edu.upc.repositories.ITrabajoRepository;
import pe.edu.upc.serviceinterface.ITrabajoService;

@Service
public class TrabajoServiceImplement implements ITrabajoService{

	@Autowired
	private ITrabajoRepository tR;

	@Override
	@Transactional
	public boolean insert(Trabajo trabajo) {
		Trabajo objTrabajo = tR.save(trabajo);
		if (objTrabajo==null) {
			return false;
		}
		return true;
	}

	@Override
	public List<Trabajo> list() {
		// TODO Auto-generated method stub
		return tR.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Trabajo listarId(int idTrabajo) {
		Optional<Trabajo> op = tR.findById(idTrabajo);
		return op.isPresent() ? op.get() : new Trabajo();
	}
	@Override
	@Transactional
	public Optional<Trabajo> findById(int id) {
		return tR.findById(id);

	}
}