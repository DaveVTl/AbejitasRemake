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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Freelancer")
public class Freelancers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFreelancers;

	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "Escriba un nombre correcto")
	@Pattern(regexp = "[^0-9]+", message = "Escriba un nombre sin numeros")
	@Column(name = "nameFreelancers", length = 45, nullable = false)
	private String nameFreelancers;

	@Pattern(regexp = "[0-9]{8}", message = "Escriba un Dni valido")
	@Column(name = "dniFreelancers", nullable = false, length = 15)
	private String dniFreelancers;

	@Column(name = "descriptionFreelancers", nullable = false, length = 254)
	private String descriptionFreelancers;

	@Column(name = "CVFreelancers", nullable = false, length = 100)
	private String CVFreelancers;

	@Pattern(regexp = "[0-9]{9}", message = "Escriba un numero valido")
	@Column(name = "number", nullable = false, length = 15)
	private String number;

	@Column(name = "Address", nullable = false, length = 100)
	private String Address;

	@Column(name = "email", nullable = false, length = 100)
	private String email;

	@Column(name = "fotoFreelancers", nullable = true)
	private String fotoFreelancers;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaInscripcionFreelancers;

	@OneToOne(cascade = CascadeType.ALL)
	private Usuario usuario;

	public Freelancers() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Freelancers(int idFreelancers,
			@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "Escriba un nombre correcto") @Pattern(regexp = "[^0-9]+", message = "Escriba un nombre sin numeros") String nameFreelancers,
			@Pattern(regexp = "[0-9]{8}", message = "Escriba un Dni valido") String dniFreelancers,
			String descriptionFreelancers, String cVFreelancers,
			@Pattern(regexp = "[0-9]{8}", message = "Escriba un numero valido") String number, String address,
			String email, String fotoFreelancers, Date fechaInscripcionFreelancers, Usuario usuario) {
		super();
		this.idFreelancers = idFreelancers;
		this.nameFreelancers = nameFreelancers;
		this.dniFreelancers = dniFreelancers;
		this.descriptionFreelancers = descriptionFreelancers;
		CVFreelancers = cVFreelancers;
		this.number = number;
		Address = address;
		this.email = email;
		this.fotoFreelancers = fotoFreelancers;
		this.fechaInscripcionFreelancers = fechaInscripcionFreelancers;
		this.usuario = usuario;
	}

	public int getIdFreelancers() {
		return idFreelancers;
	}

	public void setIdFreelancers(int idFreelancers) {
		this.idFreelancers = idFreelancers;
	}

	public String getNameFreelancers() {
		return nameFreelancers;
	}

	public void setNameFreelancers(String nameFreelancers) {
		this.nameFreelancers = nameFreelancers;
	}

	public String getDniFreelancers() {
		return dniFreelancers;
	}

	public void setDniFreelancers(String dniFreelancers) {
		this.dniFreelancers = dniFreelancers;
	}

	public String getDescriptionFreelancers() {
		return descriptionFreelancers;
	}

	public void setDescriptionFreelancers(String descriptionFreelancers) {
		this.descriptionFreelancers = descriptionFreelancers;
	}

	public String getCVFreelancers() {
		return CVFreelancers;
	}

	public void setCVFreelancers(String cVFreelancers) {
		CVFreelancers = cVFreelancers;
	}

	public String getFotoFreelancers() {
		return fotoFreelancers;
	}

	public void setFotoFreelancers(String fotoFreelancers) {
		this.fotoFreelancers = fotoFreelancers;
	}

	public Date getFechaInscripcionFreelancers() {
		return fechaInscripcionFreelancers;
	}

	public void setFechaInscripcionFreelancers(Date fechaInscripcionFreelancers) {
		this.fechaInscripcionFreelancers = fechaInscripcionFreelancers;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
