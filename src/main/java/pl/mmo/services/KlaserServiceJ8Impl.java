package pl.mmo.services;


import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import pl.mmo.entities.Film;
import pl.mmo.entities.Status;
import pl.mmo.repositories.MoviesAlreadyExistsException;
import pl.mmo.repositories.FilmyRepository;
import pl.mmo.repositories.NoSuchMovieException;


@Service
@Primary
public class KlaserServiceJ8Impl implements KlaserService {

    @Qualifier("lista")
    private FilmyRepository filmy;

    @Override
    public List<Film> findAll() {
        return filmy.findAll();
    }

    @Override
    public List<Film> findLatest3() {
        return filmy.findAll().stream().sorted((a, b) -> b.getDataPolskiejPremiery().compareTo(a.getDataPolskiejPremiery())).limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Film> findById(Long id) {
        try {
            return Optional.of(filmy.readById(id));
        } catch (NoSuchMovieException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Film> create(Film film) {
        try {
            return Optional.of(filmy.create(film));
        } catch (MoviesAlreadyExistsException e) {
            try {
                return Optional.of(filmy.readById(film.getId()));
            } catch (NoSuchMovieException e1) {
                return Optional.empty();
            }
        }

    }

    @Override
    public Optional<Film> edit(Film film) {
        try {
            return Optional.of(filmy.update(film));
        } catch (NoSuchMovieException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        try {
            filmy.deleteById(id);
            return Optional.of(Boolean.TRUE);
        } catch (NoSuchMovieException e) {
            return Optional.of(Boolean.FALSE);
        }
    }

    @Override
    public List<Film> findAllToSell() {
        return filmy.findAll().stream().filter(p -> Objects.equals(p.getStatus(), Status.DO_WYPOZYCZENIA))
                .collect(Collectors.toList());
    }
}