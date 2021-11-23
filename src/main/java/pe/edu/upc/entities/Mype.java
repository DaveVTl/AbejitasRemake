package pe.edu.upc.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "Mypes")
public class Mype {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMype;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "Escriba un nombre correcto")
	@Pattern(regexp = "[^0-9]+", message = "Escriba un nombre sin numeros")
	@Column(name = "nameEmpresaMype", length = 35, nullable=false)
	private String nameEmpresaMype;
	
	@Pattern(regexp = "[0-9]{11}", message = "Escriba un RUC valido")
	@Column(name = "rucMype", length = 11, nullable=false)
	private String rucMype;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "Escriba un nombre correcto")
	@Pattern(regexp = "[^0-9]+", message = "Escriba un nombre sin numeros")
	@Column(name = "nameGerenteMype", length = 255, nullable=false)
	private String nameGerenteMype;
	
	@Column(name = "logoMype", nullable = true)
	private String logoMype;
	
	private Date fechaInscripcionMype;

	@OneToOne(cascade = CascadeType.ALL)
	private Usuario usuario;

	
	public Mype() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public Mype(int idMype,
			@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "Escriba un nombre correcto") @Pattern(regexp = "[^0-9]+", message = "Escriba un nombre sin numeros") String nameEmpresaMype,
			@Pattern(regexp = "[0-9]{11}", message = "Escriba un RUC valido") String rucMype,
			@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "Escriba un nombre correcto") @Pattern(regexp = "[^0-9]+", message = "Escriba un nombre sin numeros") String nameGerenteMype,
			String logoMype, Date fechaInscripcionMype, Usuario usuario) {
		super();
		this.idMype = idMype;
		this.nameEmpresaMype = nameEmpresaMype;
		this.rucMype = rucMype;
		this.nameGerenteMype = nameGerenteMype;
		this.logoMype = logoMype;
		this.fechaInscripcionMype = fechaInscripcionMype;
		this.usuario = usuario;
	}



	public int getIdMype() {
		return idMype;
	}

	public void setIdMype(int idMype) {
		this.idMype = idMype;
	}

	public String getNameEmpresaMype() {
		return nameEmpresaMype;
	}

	public void setNameEmpresaMype(String nameEmpresaMype) {
		this.nameEmpresaMype = nameEmpresaMype;
	}

	public String getRucMype() {
		return rucMype;
	}

	public void setRucMype(String rucMype) {
		this.rucMype = rucMype;
	}

	public String getNameGerenteMype() {
		return nameGerenteMype;
	}

	public void setNameGerenteMype(String nameGerenteMype) {
		this.nameGerenteMype = nameGerenteMype;
	}

	public String getLogoMype() {
		return logoMype;
	}

	public void setLogoMype(String logoMype) {
		this.logoMype = logoMype;
	}

	public Date getFechaInscripcionMype() {
		return fechaInscripcionMype;
	}

	public void setFechaInscripcionMype(Date fechaInscripcionMype) {
		this.fechaInscripcionMype = fechaInscripcionMype;
	}
	
	
	
}
