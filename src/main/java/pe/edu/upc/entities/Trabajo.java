package pe.edu.upc.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "trabajos")
public class Trabajo {

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
	
	@NotNull
	@DecimalMin("1.00")
	@Positive
	@Column(name = "pagoTrabajo",columnDefinition = "Decimal(8,2)", nullable = false)
	private Double pagoTrabajo;
	
	@ManyToOne
	@JoinColumn(name = "idFreelancers", nullable = false)
	private Freelancers freelancers;

	@ManyToOne
	@JoinColumn(name = "idAnuncio", nullable = false)
	private Anuncio anuncio;

	@ManyToOne
	@JoinColumn(name = "idTipoPago", nullable = false)
	private TipoPago tipoPago;

	public Trabajo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Trabajo(int idTrabajo, String nameTrabajo, String descriptionTrabajo, String estadoTrabajo,
			Date dateCreacionAnuncio, @NotNull @DecimalMin("1.00") @Positive Double pagoTrabajo,
			Freelancers freelancers, Anuncio anuncio, TipoPago tipoPago) {
		super();
		this.idTrabajo = idTrabajo;
		this.nameTrabajo = nameTrabajo;
		this.descriptionTrabajo = descriptionTrabajo;
		EstadoTrabajo = estadoTrabajo;
		this.dateCreacionAnuncio = dateCreacionAnuncio;
		this.pagoTrabajo = pagoTrabajo;
		this.freelancers = freelancers;
		this.anuncio = anuncio;
		this.tipoPago = tipoPago;
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

	public Double getPagoTrabajo() {
		return pagoTrabajo;
	}

	public void setPagoTrabajo(Double pagoTrabajo) {
		this.pagoTrabajo = pagoTrabajo;
	}

	public Freelancers getFreelancers() {
		return freelancers;
	}

	public void setFreelancers(Freelancers freelancers) {
		this.freelancers = freelancers;
	}

	public Anuncio getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}

	public TipoPago getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(TipoPago tipoPago) {
		this.tipoPago = tipoPago;
	}
	
	

	

	



}
