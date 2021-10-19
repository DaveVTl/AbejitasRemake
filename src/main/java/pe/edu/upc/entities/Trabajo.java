package pe.edu.upc.entities;

import java.io.Serializable;
import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.Table;

@Entity
@Table(name = "Trabajo")
public class Trabajo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTrabajo;

	@Column(name = "nameTrabajo", length = 45, nullable = false)
	private String nameTrabajo;

	@Column(name = "descriptionTrabajo", nullable = false, length = 254)
	private String descriptionTrabajo;

	@Column(name = "EstadoTrabajo", nullable = false, length = 10)
	private String EstadoTrabajo;

	private Date dateCreacionAnuncio;

	@Column(name = "nuevoPagoTrabajo", nullable = false)
	private double nuevoPagoTrabajo;

	
	@JoinColumn(name = "idFreelancers", nullable = false)
	private int freelancers;

	public Trabajo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param idTrabajo
	 * @param nameTrabajo
	 * @param descriptionTrabajo
	 * @param estadoTrabajo
	 * @param dateCreacionAnuncio
	 * @param nuevoPagoTrabajo
	 * @param freelancers
	 */
	public Trabajo(int idTrabajo, String nameTrabajo, String descriptionTrabajo, String estadoTrabajo,
			Date dateCreacionAnuncio, double nuevoPagoTrabajo, int freelancers) {
		super();
		this.idTrabajo = idTrabajo;
		this.nameTrabajo = nameTrabajo;
		this.descriptionTrabajo = descriptionTrabajo;
		EstadoTrabajo = estadoTrabajo;
		this.dateCreacionAnuncio = dateCreacionAnuncio;
		this.nuevoPagoTrabajo = nuevoPagoTrabajo;
		this.freelancers = freelancers;
	}

	public int getIdTrabajo() {
		return idTrabajo;
	}

	public void setIdTrabajo(int idTrabajo) {
		this.idTrabajo = idTrabajo;
	}

	public String getNameTrabajo() {
		return nameTrabajo;
	}

	public void setNameTrabajo(String nameTrabajo) {
		this.nameTrabajo = nameTrabajo;
	}

	public String getDescriptionTrabajo() {
		return descriptionTrabajo;
	}

	public void setDescriptionTrabajo(String descriptionTrabajo) {
		this.descriptionTrabajo = descriptionTrabajo;
	}

	public String getEstadoTrabajo() {
		return EstadoTrabajo;
	}

	public void setEstadoTrabajo(String estadoTrabajo) {
		EstadoTrabajo = estadoTrabajo;
	}

	public Date getDateCreacionAnuncio() {
		return dateCreacionAnuncio;
	}

	public void setDateCreacionAnuncio(Date dateCreacionAnuncio) {
		this.dateCreacionAnuncio = dateCreacionAnuncio;
	}

	public double getNuevoPagoTrabajo() {
		return nuevoPagoTrabajo;
	}

	public void setNuevoPagoTrabajo(double nuevoPagoTrabajo) {
		this.nuevoPagoTrabajo = nuevoPagoTrabajo;
	}

	public int getFreelancers() {
		return freelancers;
	}

	public void setFreelancers(int freelancers) {
		this.freelancers = freelancers;
	}

	



}
