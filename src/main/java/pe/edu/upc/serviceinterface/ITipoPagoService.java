package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entities.TipoPago;

public interface ITipoPagoService {

	public Integer insert(TipoPago pago);

	List<TipoPago> list();

	TipoPago listarId(int idpago);

	public void delete(int idpago);
}
