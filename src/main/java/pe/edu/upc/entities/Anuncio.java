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

@Entity
@Table(name = "Anuncio")
public class Anuncio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAnuncio;
	@Column(name = "nameAnuncio", length = 45, nullable = false)
	private String nameAnuncio	;
	@Column(name = "descriptionAnuncio", nullable = false, length = 254)
	private String descriptionAnuncio;
	private Date dateCreacionAnuncio;
	@Column(name = "pagoAnuncio", nullable = false, length = 254)
	private String pagoAnuncio;
	
	@ManyToOne
    @JoinColumn(name="idMype", nullable = false)
    private Mype mype;
	@ManyToOne
    @JoinColumn(name="idTipoTrabajo", nullable = false)
    private TipoTrabajo tipoTrabajo;

	public Anuncio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Anuncio(int idAnuncio, String nameAnuncio, String descriptionAnuncio, Date dateCreacionAnuncio,
			String pagoAnuncio, Mype mype, TipoTrabajo tipoTrabajo) {
		super();
		this.idAnuncio = idAnuncio;
		this.nameAnuncio = nameAnuncio;
		this.descriptionAnuncio = descriptionAnuncio;
		this.dateCreacionAnuncio = dateCreacionAnuncio;
		this.pagoAnuncio = pagoAnuncio;
		this.mype = mype;
		this.tipoTrabajo = tipoTrabajo;
	}

	public int getIdAnuncio() {
		return idAnuncio;
	}

	public void setIdAnuncio(int idAnuncio) {
		this.idAnuncio = idAnuncio;
	}

	public String getNameAnuncio() {
		return nameAnuncio;
	}

	public void setNameAnuncio(String nameAnuncio) {
		this.nameAnuncio = nameAnuncio;
	}

	public String getDescriptionAnuncio() {
		return descriptionAnuncio;
	}

	public void setDescriptionAnuncio(String descriptionAnuncio) {
		this.descriptionAnuncio = descriptionAnuncio;
	}

	public Date getDateCreacionAnuncio() {
		return dateCreacionAnuncio;
	}

	public void setDateCreacionAnuncio(Date dateCreacionAnuncio) {
		this.dateCreacionAnuncio = dateCreacionAnuncio;
	}

	public String getPagoAnuncio() {
		return pagoAnuncio;
	}

	public void setPagoAnuncio(String pagoAnuncio) {
		this.pagoAnuncio = pagoAnuncio;
	}

	public Mype getMypes() {
		return mype;
	}

	public void setMypes(Mype mype) {
		this.mype = mype;
	}

	public TipoTrabajo getTipoTrabajo() {
		return tipoTrabajo;
	}

	public void setTipoTrabajo(TipoTrabajo tipoTrabajo) {
		this.tipoTrabajo = tipoTrabajo;
	}
=======
>>>>>>> 422c72c45fb144c5edf6f95e28ce78460d0d83ab

}
