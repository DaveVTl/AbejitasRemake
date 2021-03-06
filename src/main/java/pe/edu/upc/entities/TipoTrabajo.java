package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "TipoTrabajo")
public class TipoTrabajo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTipoTrabajo;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "Escriba un nombre correcto")
	@Pattern(regexp = "[^0-9]+", message = "Escriba un nombre sin numeros")
	@Column(name = "nombreTrabajo",length=35,nullable=false)
	private String nombreTrabajo;

	public TipoTrabajo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipoTrabajo(int idTipoTrabajo,
			@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "Escriba un nombre correcto") @Pattern(regexp = "[^0-9]+", message = "Escriba un nombre sin numeros") String nombreTrabajo) {
		super();
		this.idTipoTrabajo = idTipoTrabajo;
		this.nombreTrabajo = nombreTrabajo;
	}

	public int getIdTipoTrabajo() {
		return idTipoTrabajo;
	}

	public void setIdTipoTrabajo(int idTipoTrabajo) {
		this.idTipoTrabajo = idTipoTrabajo;
	}

	public String getNombreTrabajo() {
		return nombreTrabajo;
	}

	public void setNombreTrabajo(String nombreTrabajo) {
		this.nombreTrabajo = nombreTrabajo;
	}

	
}
