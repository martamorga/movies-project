package pl.mmo.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import pl.mmo.entities.Film;
import pl.mmo.entities.Status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Repository
@Qualifier("tablica")
public class ProstaBazaDanych implements FilmyRepository {

    private Film[] baza;

    public ProstaBazaDanych() {
        baza = new Film[15];
        Film m = new Film();
        m.setId(0L);
        m.setGatunek("Dramat");
        m.setTytul("Taki fajny tytuł");
        m.setAutor("Marta Morga");
        m.setKrajProdukcji("Polska");
        m.setDataDodaniaFilmu(new Date());
        m.setCenaWypozyczenia(new BigDecimal("1.2"));
        m.setStatus(Status.DO_WYPOZYCZENIA);
        baza[0] = m;
        m.setId(2L);
        m.setGatunek("Komedia");
        m.setTytul("tytul2");
        m.setAutor("Marta Morga2");
        m.setKrajProdukcji("Polska2");
        m.setDataDodaniaFilmu(new Date());
        m.setCenaWypozyczenia(new BigDecimal("1.5"));
        m.setStatus(Status.KILKA_DOSTEPNYCH_SZTUK);
        baza[2] = m;

    }

    public ProstaBazaDanych(int rozmiarBazy) {
        baza = new Film[rozmiarBazy];
    }

    @Override
    public Film create(Film film) throws MoviesAlreadyExistsException {
        if (film.getId() != null && baza[film.getId().intValue()] != null) {
            if (film.getId().equals(baza[film.getId().intValue()].getId())) {
                throw new MoviesAlreadyExistsException("Już jest film o takim numerze.");
            }
        }
        for (int i = 0; i < baza.length; i++) {
            if (baza[i] == null) {
                baza[i] = film;
                film.setId((long) i);
                return film;
            }
        }
        throw new RuntimeException("Brak miejsca w tablicy");
    }

    @Override
    public void deleteById(Long id) throws NoSuchMovieException {
        int numerKatalogowy = id.intValue();
        if (!sprawdzPoprawnoscNumeruId(numerKatalogowy)) {
            throw new NoSuchMovieException("Nie poprawny numer katologowy");
        }
        // tu troche zle ;)
        baza[numerKatalogowy] = null;
    }

    @Override
    public Film update(Film moneta) throws NoSuchMovieException {
        int numerKatalogowy = moneta.getId().intValue();
        if (!sprawdzPoprawnoscNumeruId(numerKatalogowy)) {
            throw new NoSuchMovieException("Nie poprawny numer katologowy");
        }

        Film m = baza[moneta.getId().intValue()];
        if (m == null) {
            throw new NoSuchMovieException("Brak takiej monety.");
        } else {
            baza[moneta.getId().intValue()] = moneta;
        }
        return moneta;
    }

    @Override
    public Film readById(Long numerKatalogowy) throws NoSuchMovieException {
        int id = numerKatalogowy.intValue();
        if (!sprawdzPoprawnoscNumeruId(id) || czyWolne(id)) {
            throw new NoSuchMovieException();
        }
        return baza[id];
    }

    private boolean czyWolne(int id) {
        if(baza[id]!= null)
            return false;
        return true;
    }

    @Override
    public List<Film> findAll() {
        List<Film> tmp = new ArrayList<>();
        for (int i = 0; i < baza.length; i++) {
            if (baza[i] != null)
                tmp.add(baza[i]);
        }
        return tmp;
    }

    public void wyswietlBaze() {
        for (int i = 0; i < baza.length; i++) {
            System.out.println("" + i + ":" + baza[i]);
        }
    }

    private boolean sprawdzPoprawnoscNumeruId(int id) {
        if (id < 0 || id >= baza.length) {
            System.out.println("Zły numer katalogowy");
            return false;
        }
        return true;
    }

}
