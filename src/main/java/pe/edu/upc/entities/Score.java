package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "scores")
public class Score {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // ALLI ESTÁ A, XD
	private int idScore;
	
	@Positive
	@Column(name = "number", length = 2, nullable = false)
	private String number;

	public Score() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Score(int idScore, @Positive String number) {
		super();
		this.idScore = idScore;
		this.number = number;
	}

	public int getIdScore() {
		return idScore;
	}

	public void setIdScore(int idScore) {
		this.idScore = idScore;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	
}
