package pl.mmo.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pl.mmo.entities.Film;
import pl.mmo.entities.Status;


@Service
@Qualifier("lista")
public class FilmyRepositoryJ8Impl implements FilmyRepository {

    private List<Film> monety = new ArrayList<Film>() {
        {

            //Long id, String tytul, String autor, String krajPochodzenia, String gatunek,
            //Date dataPolskiejPremiery, BigDecimal cenaWypozyczenia, Status status
            add(Film.dodajFilm(1L, "Skazani na Shawshank", "Frank Darabont", "USA", "Dramat", new Date("2017-02-02"), new BigDecimal("5.5"),
                    Status.WYPOZYCZONY));
            add(Film.dodajFilm(2L, "Nietykalni", "Olivier Nakache, Eric Toledano", "Francja", "Biograficzny, Dramat, Komedia", new Date(), new BigDecimal("1.2"),
                    Status.DO_WYPOZYCZENIA));
            add(Film.dodajFilm(3L, "Zielona mila", "Frank Darabont", "USA", "Dramat", new Date(), new BigDecimal("1.8"), Status.KILKA_DOSTEPNYCH_SZTUK));
            add(Film.dodajFilm(4L, "Ojciec chrzestny", "Mario Puzo, Francis Ford Coppola", "USA", "Dramat, Gangsterski", new Date(), new BigDecimal("1.4"),
                    Status.DO_WYPOZYCZENIA));
            add(Film.dodajFilm(5L, "Forrest Gump", "Robert Zemeckis", "USA", "Dramat, Komedia", new Date(), new BigDecimal("2.2"), Status.WYPOZYCZONY));
            add(Film.dodajFilm(6L, "Dwunastu gniewnych ludzi", "Sidney Lumet", "USA", "Dramat sądowy", new Date(), new BigDecimal("1.2"), Status.WYPOZYCZONY));
        }
    };

    @Override
    public List<Film> findAll() {
        return this.monety;
    }

    @Override
    public Film readById(Long id) throws NoSuchMovieException {
        return this.monety.stream().filter(p -> Objects.equals(p.getId(), id)).findFirst()
                .orElseThrow(NoSuchMovieException::new);
    }

    @Override
    public Film create(Film film) {
        if (!monety.isEmpty()) {
            film.setId(
                    this.monety.stream().mapToLong(p -> p.getId()).max().getAsLong() + 1);
        } else {
            film.setId(1L);
        }
        this.monety.add(film);
        return film;
    }

    @Override
    public Film update(Film film) throws NoSuchMovieException {
        for (int i = 0; i < this.monety.size(); i++) {
            if (Objects.equals(this.monety.get(i).getId(), film.getId())) {
                this.monety.set(i, film);
                return film;
            }
        }
        throw new NoSuchMovieException("Nie ma takiego filmu: " + film.getId());
    }

    @Override
    public void deleteById(Long id) throws NoSuchMovieException {
        for (int i = 0; i < this.monety.size(); i++) {
            if (Objects.equals(this.monety.get(i).getId(), id)) {
                this.monety.remove(i);
            }
        }
        throw new NoSuchMovieException("Nie ma takiego filmu: " + id);
    }

}