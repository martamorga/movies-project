package pl.mmo.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Film {

    private Long id;

    @NotEmpty
    private String gatunek;

    @NotEmpty
    private String tytul;

    @NotEmpty
    private String autor;

    @NotNull
    private BigDecimal cenaWypozyczenia;

    private Date dataDodaniaFilmu;

    @NotEmpty
    private String krajProdukcji;

    @NotNull
    private Status status;


    public static Film dodajFilm(Long id, String tytul, String autor, String krajPochodzenia, String gatunek,
                                 Date dataDodaniaFilmu, BigDecimal cenaWypozyczenia, Status status) {
        Film f = new Film();
        f.id = id;
        f.krajProdukcji = krajPochodzenia;
        f.gatunek = gatunek;
        f.tytul = tytul;
        f.autor = autor;
        f.cenaWypozyczenia = cenaWypozyczenia;
        f.dataDodaniaFilmu = dataDodaniaFilmu;
        f.status = status;
        return f;
    }

    public Long getId() {
        return id;
    }

    public String getGatunek() {
        return gatunek;
    }

    public String getTytul() {
        return tytul;
    }

    public String getAutor() {
        return autor;
    }

    public BigDecimal getCenaWypozyczenia() {
        return cenaWypozyczenia;
    }

    public Date getDataDodaniaFilmu() {
        return dataDodaniaFilmu;
    }

    public String getKrajProdukcji() {
        return krajProdukcji;
    }

    public Status getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGatunek(String gatunek) {
        this.gatunek = gatunek;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setCenaWypozyczenia(BigDecimal cenaWypozyczenia) {
        this.cenaWypozyczenia = cenaWypozyczenia;
    }

    public void setDataDodaniaFilmu(Date dataDodaniaFilmu) {
        this.dataDodaniaFilmu = dataDodaniaFilmu;
    }

    public void setKrajProdukcji(String krajProdukcji) {
        this.krajProdukcji = krajProdukcji;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((tytul == null) ? 0 : tytul.hashCode());
        result = prime * result + ((cenaWypozyczenia == null) ? 0 : cenaWypozyczenia.hashCode());
        result = prime * result + ((dataDodaniaFilmu == null) ? 0 : dataDodaniaFilmu.hashCode());
        result = prime * result + ((krajProdukcji == null) ? 0 : krajProdukcji.hashCode());
        result = prime * result + ((gatunek == null) ? 0 : gatunek.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((autor == null) ? 0 : autor.hashCode());
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
        Film other = (Film) obj;
        if (tytul == null) {
            if (other.tytul != null)
                return false;
        } else if (!tytul.equals(other.tytul))
            return false;
        if (cenaWypozyczenia == null) {
            if (other.cenaWypozyczenia != null)
                return false;
        } else if (!cenaWypozyczenia.equals(other.cenaWypozyczenia))
            return false;
        if (dataDodaniaFilmu == null) {
            if (other.dataDodaniaFilmu != null)
                return false;
        } else if (!dataDodaniaFilmu.equals(other.dataDodaniaFilmu))
            return false;
        if (krajProdukcji == null) {
            if (other.krajProdukcji != null)
                return false;
        } else if (!krajProdukcji.equals(other.krajProdukcji))
            return false;
        if (gatunek == null) {
            if (other.gatunek != null)
                return false;
        } else if (!gatunek.equals(other.gatunek))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (autor == null) {
            if (other.autor != null)
                return false;
        } else if (!autor.equals(other.autor))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Film [id=" + id + ", gatunek=" + gatunek + ", Tytul=" + tytul + ", autor="
                + autor + ", cenaWypozyczenia=" + cenaWypozyczenia + ", dataDodaniaFilmu=" + dataDodaniaFilmu + ", krajProdukcji="
                + krajProdukcji + ", status=" + status + "]";
    }

}