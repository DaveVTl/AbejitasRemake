package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "TipoPago")
public class TipoPago {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTipoPago;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "Escriba un nombre correcto")
	@Pattern(regexp = "[^0-9]+", message = "Escriba un nombre sin numeros")
	@Column(name = "nombrePago",length=35,nullable=false)
	private String nombrePago;

	public TipoPago() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipoPago(int idTipoPago, String nombrePago) {
		super();
		this.idTipoPago = idTipoPago;
		this.nombrePago = nombrePago;
	}

	public int getIdTipoPago() {
		return idTipoPago;
	}

	public void setIdTipoPago(int idTipoPago) {
		this.idTipoPago = idTipoPago;
	}

	public String getNombrePago() {
		return nombrePago;
	}

	public void setNombrePago(String nombrePago) {
		this.nombrePago = nombrePago;
	}
	
	
}
