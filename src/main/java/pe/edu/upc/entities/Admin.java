package pe.edu.upc.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Admin")

public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAdmin;
	@Column(name = "nameAdmin", length = 35, nullable=false)
	private String nameAdmin;
	@Column(name = "DNI", length = 35, nullable=false)
	private String DNI;
	private Date dateInicio;

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(int idAdmin, String nameAdmin, String dNI, Date dateInicio) {
		super();
		this.idAdmin = idAdmin;
		this.nameAdmin = nameAdmin;
		DNI = dNI;
		this.dateInicio = dateInicio;
	}

	public int getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}

	public String getNameAdmin() {
		return nameAdmin;
	}

	public void setNameAdmin(String nameAdmin) {
		this.nameAdmin = nameAdmin;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public Date getdateInicio() {
		return dateInicio;
	}

	public void setdateInicio(Date dateInicio) {
		this.dateInicio = dateInicio;
	}
	
}
