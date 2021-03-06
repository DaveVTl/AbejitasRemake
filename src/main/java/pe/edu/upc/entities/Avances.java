package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "avances")
public class Avances {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAvance;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "Escriba un nombre correcto")
	@Pattern(regexp = "[^0-9]+", message = "Escriba un nombre sin numeros")
	@Column(name = "nombre", nullable = false)
	private String nombre;
	@Column(name = "fotoavance", nullable = true)
	private String fotoavance;
	@Column(name = "fotoavance2", nullable = true)
	private String fotoavance2;
	@Column(name = "descripcion", nullable = false, length = 100)
	private String descripcion;
	@ManyToOne
	@JoinColumn(name = "idTrabajo", nullable = false)
	private Trabajo trabajo;
	
	public Avances() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Avances(int idAvance,
			@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "Escriba un nombre correcto") @Pattern(regexp = "[^0-9]+", message = "Escriba un nombre sin numeros") String nombre,
			String fotoavance, String fotoavance2, String descripcion, Trabajo trabajo) {
		super();
		this.idAvance = idAvance;
		this.nombre = nombre;
		this.fotoavance = fotoavance;
		this.fotoavance2 = fotoavance2;
		this.descripcion = descripcion;
		this.trabajo = trabajo;
	
	}
	public int getIdAvance() {
		return idAvance;
	}
	public void setIdAvance(int idAvance) {
		this.idAvance = idAvance;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFotoavance() {
		return fotoavance;
	}
	public void setFotoavance(String fotoavance) {
		this.fotoavance = fotoavance;
	}
	public String getFotoavance2() {
		return fotoavance2;
	}
	public void setFotoavance2(String fotoavance2) {
		this.fotoavance2 = fotoavance2;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Trabajo getTrabajo() {
		return trabajo;
	}
	public void setTrabajo(Trabajo trabajo) {
		this.trabajo = trabajo;
	}
	

}
