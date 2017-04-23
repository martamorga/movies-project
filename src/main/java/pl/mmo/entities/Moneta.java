package pl.mmo.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

//@Entity
//@XmlRootElement
public class Moneta {

//    @NotNull
//    @Id
    private Long numerKatalogowy;
	
//	@NotNull
	private Long nominal;
	
	//@Size(min=2, max=30, message = "{Size.moneta.opis}")
//	@Size(min=2, max=30, message = "Opis should be in the range [{min}...{max}]")
	private String opis;
//	@NotEmpty
	private String waluta;
//	@NotNull
	private BigDecimal cenaNabycia;
//	@NotNull
	private Date dataNabycia;
//	@NotEmpty
	private String krajPochodzenia;
//	@NotNull
	private Status status;

	
	
	public static Moneta produceMoneta(Long numerKatalogowy, String krajPochodzenia, Long nominal, String waluta, String opis,
			Date dataNabycia, BigDecimal cenaNabycia, Status status) {
		Moneta m = new Moneta();
		m.numerKatalogowy = numerKatalogowy;
		m.krajPochodzenia = krajPochodzenia;
		m.nominal = nominal;
		m.opis = opis;
		m.waluta = waluta;
		m.cenaNabycia = cenaNabycia;
		m.dataNabycia = dataNabycia;
		m.status = status;
		return m;
	}

	public Long getNumerKatalogowy() {
		return numerKatalogowy;
	}

	public Long getNominal() {
		return nominal;
	}

	public String getOpis() {
		return opis;
	}

	public String getWaluta() {
		return waluta;
	}

	public BigDecimal getCenaNabycia() {
		return cenaNabycia;
	}

	public Date getDataNabycia() {
		return dataNabycia;
	}

	public String getKrajPochodzenia() {
		return krajPochodzenia;
	}

	public Status getStatus() {
		return status;
	}

	public void setNumerKatalogowy(Long numerKatalogowy) {
		this.numerKatalogowy = numerKatalogowy;
	}

	public void setNominal(Long nominal) {
		this.nominal = nominal;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public void setWaluta(String waluta) {
		this.waluta = waluta;
	}

	public void setCenaNabycia(BigDecimal cenaNabycia) {
		this.cenaNabycia = cenaNabycia;
	}

	public void setDataNabycia(Date dataNabycia) {
		this.dataNabycia = dataNabycia;
	}

	public void setKrajPochodzenia(String krajPochodzenia) {
		this.krajPochodzenia = krajPochodzenia;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((opis == null) ? 0 : opis.hashCode());
		result = prime * result + ((cenaNabycia == null) ? 0 : cenaNabycia.hashCode());
		result = prime * result + ((dataNabycia == null) ? 0 : dataNabycia.hashCode());
		result = prime * result + ((krajPochodzenia == null) ? 0 : krajPochodzenia.hashCode());
		result = prime * result + ((nominal == null) ? 0 : nominal.hashCode());
		result = prime * result + ((numerKatalogowy == null) ? 0 : numerKatalogowy.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((waluta == null) ? 0 : waluta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Moneta other = (Moneta) obj;
		if (opis == null) {
			if (other.opis != null)
				return false;
		} else if (!opis.equals(other.opis))
			return false;
		if (cenaNabycia == null) {
			if (other.cenaNabycia != null)
				return false;
		} else if (!cenaNabycia.equals(other.cenaNabycia))
			return false;
		if (dataNabycia == null) {
			if (other.dataNabycia != null)
				return false;
		} else if (!dataNabycia.equals(other.dataNabycia))
			return false;
		if (krajPochodzenia == null) {
			if (other.krajPochodzenia != null)
				return false;
		} else if (!krajPochodzenia.equals(other.krajPochodzenia))
			return false;
		if (nominal == null) {
			if (other.nominal != null)
				return false;
		} else if (!nominal.equals(other.nominal))
			return false;
		if (numerKatalogowy == null) {
			if (other.numerKatalogowy != null)
				return false;
		} else if (!numerKatalogowy.equals(other.numerKatalogowy))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (waluta == null) {
			if (other.waluta != null)
				return false;
		} else if (!waluta.equals(other.waluta))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Moneta [numerKatalogowy=" + numerKatalogowy + ", nominal=" + nominal + ", Opis=" + opis + ", waluta="
				+ waluta + ", cenaNabycia=" + cenaNabycia + ", dataNabycia=" + dataNabycia + ", krajPochodzenia="
				+ krajPochodzenia + ", status=" + status + "]";
	}

}