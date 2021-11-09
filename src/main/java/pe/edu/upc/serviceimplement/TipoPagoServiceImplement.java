package pe.edu.upc.serviceimplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import pe.edu.upc.entities.TipoPago;
import pe.edu.upc.repositories.ITipoPagoRepository;
import pe.edu.upc.serviceinterface.ITipoPagoService;

@Service
public class TipoPagoServiceImplement implements ITipoPagoService {
	@Autowired
	private ITipoPagoRepository pR;

	@Override
	public Integer insert(TipoPago pago) {
		int rpta=pR.PagoExistente(pago.getNombrePago());
		if (rpta==0) {
			pR.save(pago);
		}
		return rpta;
	}
	
	@Override
	public List<TipoPago> list() {
		// TODO Auto-generated method stub
		return pR.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public TipoPago listarId(int idpago) {
		Optional<TipoPago> op = pR.findById(idpago);
		return op.isPresent() ? op.get() : new TipoPago();
	}
	
	@Override
	@Transactional
	public void delete(int idreview) {
		pR.deleteById(idreview);
	}
}
