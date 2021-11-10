package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "reviews")
public class Reviews {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // ALLI ESTÁ A, XD
	private int idReview;

	@Column(name = "descripcionre", length = 80, nullable = false)
	private String descripcionre;


	@ManyToOne
	@JoinColumn(name = "idScore", nullable = false)
	private Score score;
	
	@ManyToOne
	@JoinColumn(name = "idTrabajo", nullable = false)
	private Trabajo trabajo;
	@ManyToOne
	@JoinColumn(name = "idmype", nullable = false)
	private Mype mype;
	@ManyToOne
	@JoinColumn(name = "idfreelancer", nullable = false)
	private Freelancers freelancer;
	public Reviews() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reviews(int idReview, String descripcionre, Score score, Trabajo trabajo) {
		super();
		this.idReview = idReview;
		this.descripcionre = descripcionre;
		this.score = score;
		this.trabajo = trabajo;
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

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public Trabajo getTrabajo() {
		return trabajo;
	}

	public void setTrabajo(Trabajo trabajo) {
		this.trabajo = trabajo;
	}

}