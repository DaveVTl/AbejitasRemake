package pe.edu.upc.serviceimplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.TipoPago;
import pe.edu.upc.repositories.ITipoPagoRepository;
import pe.edu.upc.serviceinterface.ITipoPagoService;

@Service
public class TipoPagoServiceImplement implements ITipoPagoService {
	@Autowired
	private ITipoPagoRepository pR;

	@Override
	public List<TipoPago> list() {
		// TODO Auto-generated method stub
		return pR.findAll();
	}

}
