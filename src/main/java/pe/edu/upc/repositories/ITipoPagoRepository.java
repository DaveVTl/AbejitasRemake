package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.TipoPago;

@Repository
public interface ITipoPagoRepository extends JpaRepository<TipoPago,Integer>{
	@Query("select count(t.nombrePago) from TipoPago t where t.nombrePago=:nombre")
	public int PagoExistente(@Param("nombre") String nombre);
}
