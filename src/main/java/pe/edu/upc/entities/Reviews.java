package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "reviews")
public class Reviews {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)//ALLI ESTÁ A, XD
	private int idReview;
	
	@Column(name ="descripcionre", length=80, nullable=false)
	private String descripcionre;	
	
	@Positive
	@Column(name ="calificacionre", length=2, nullable=false)
	private String calificacionre;
	
	@ManyToOne
	@JoinColumn(name ="idTrabajo", nullable=false)
	private Trabajo idTrabajo;

	public Reviews() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reviews(int idReview, String descripcionre,  @Positive String calificacionre, Trabajo idTrabajo) {
		super();
		this.idReview = idReview;
		this.descripcionre = descripcionre;
		this.calificacionre = calificacionre;
		this.idTrabajo = idTrabajo;
	}

	public int getIdReview() {
		return idReview;
	}

	public void setIdReview(int idReview) {
		this.idReview = idReview;
	}

	public String getDescripcionre() {
		return descripcionre;
	}

	public void setDescripcionre(String descripcionre) {
		this.descripcionre = descripcionre;
	}

	public String getCalificacionre() {
		return calificacionre;
	}

	public void setCalificacionre(String calificacionre) {
		this.calificacionre = calificacionre;
	}

	public Trabajo getIdTrabajo() {
		return idTrabajo;
	}

	public void setIdTrabajo(Trabajo idTrabajo) {
		this.idTrabajo = idTrabajo;
	}
	
	
}
