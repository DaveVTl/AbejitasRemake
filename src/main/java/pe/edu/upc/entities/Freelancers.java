package pe.edu.upc.entities;


import java.util.Date;
import java.util.Objects;

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
@Table(name = "Freelancers")
public class Freelancers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFreelancers;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre no puede contener caracteres especiales")
	@Pattern(regexp = "[^0-9]+", message = "El nombre no puede contener un número")
	@Column(name = "nameFreelancers", length = 45, nullable = false)
	private String nameFreelancers;

	@Column(name = "dniFreelancers", nullable = false, length = 15)
	private int dniFreelancers;

	@Column(name = "descriptionFreelancers", nullable = false, length = 254)
	private String descriptionFreelancers;

	@Column(name = "CVFreelancers", nullable = false, length = 100)
	private String CVFreelancers;

	@Column(name = "fotoFreelancers", nullable = false, length = 100)
	private String fotoFreelancers;

	private Date fechaInscripcionFreelancers;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "freelancer")
	private Usuario usuario;

	/**
	 * @param idFreelancers
	 * @param nameFreelancers
	 * @param dniFreelancers
	 * @param descriptionFreelancers
	 * @param cVFreelancers
	 * @param fotoFreelancers
	 * @param fechaInscripcionFreelancers
	 * @param usuario
	 */
	public Freelancers(int idFreelancers, String nameFreelancers, int dniFreelancers, String descriptionFreelancers,
			String cVFreelancers, String fotoFreelancers, Date fechaInscripcionFreelancers, Usuario usuario) {
		super();
		this.idFreelancers = idFreelancers;
		this.nameFreelancers = nameFreelancers;
		this.dniFreelancers = dniFreelancers;
		this.descriptionFreelancers = descriptionFreelancers;
		CVFreelancers = cVFreelancers;
		this.fotoFreelancers = fotoFreelancers;
		this.fechaInscripcionFreelancers = fechaInscripcionFreelancers;
		this.usuario = usuario;
	}

	/**
	 * 
	 */
	public Freelancers() {
		super();
		// TODO Auto-generated constructor stub
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

	public int getDniFreelancers() {
		return dniFreelancers;
	}

	public void setDniFreelancers(int dniFreelancers) {
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

	@Override
	public int hashCode() {
		return Objects.hash(CVFreelancers, descriptionFreelancers, dniFreelancers, fechaInscripcionFreelancers,
				fotoFreelancers, idFreelancers, nameFreelancers, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Freelancers other = (Freelancers) obj;
		return Objects.equals(CVFreelancers, other.CVFreelancers)
				&& Objects.equals(descriptionFreelancers, other.descriptionFreelancers)
				&& dniFreelancers == other.dniFreelancers
				&& Objects.equals(fechaInscripcionFreelancers, other.fechaInscripcionFreelancers)
				&& Objects.equals(fotoFreelancers, other.fotoFreelancers) && idFreelancers == other.idFreelancers
				&& Objects.equals(nameFreelancers, other.nameFreelancers) && Objects.equals(usuario, other.usuario);
	}
	public Usuario getUser() {
		return usuario;
	}

	public void setUser(Usuario usuario) {
		this.usuario = usuario;
	}

}
