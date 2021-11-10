package pe.edu.upc.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Mypes")
public class Mype {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMype;
	@Column(name = "nameEmpresaMype", length = 35, nullable=false)
	private String nameEmpresaMype;
	@Column(name = "rucMype", length = 35, nullable=false)
	private String rucMype;
	@Column(name = "nameGerenteMype", length = 40, nullable=false)
	private String nameGerenteMype;
	@Column(name = "logoMype", length = 40, nullable=false)
	private String logoMype;
	
	public Mype() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mype(int idMype, String nameEmpresaMype, String rucMype, String nameGerenteMype, String logoMype,
			Date dateInscripcion) {
		super();
		this.idMype = idMype;
		this.nameEmpresaMype = nameEmpresaMype;
		this.rucMype = rucMype;
		this.nameGerenteMype = nameGerenteMype;
		this.logoMype = logoMype;
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

}
