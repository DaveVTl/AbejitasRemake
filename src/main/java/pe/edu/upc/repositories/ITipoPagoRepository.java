package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.TipoPago;

@Repository
public interface ITipoPagoRepository extends JpaRepository<TipoPago, String>{

}
