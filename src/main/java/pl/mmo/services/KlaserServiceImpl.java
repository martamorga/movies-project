package pl.mmo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.mmo.entities.Film;
import pl.mmo.repositories.FilmyRepository;
import pl.mmo.repositories.MoviesAlreadyExistsException;
import pl.mmo.repositories.NoSuchMovieException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
@Qualifier("tablica")
public class KlaserServiceImpl implements KlaserService {

    @Autowired
    @Qualifier("tablica")
    private FilmyRepository bazaDanych;

    @Override
    public List<Film> findAll() {
        return bazaDanych.findAll();
    }

    @Override
    public List<Film> findAllToSell() {
        return bazaDanych.findAll();
    }

    @Override
    public Optional<Film> findById(Long id) {
        try {
            return Optional.of(bazaDanych.readById(id));
        } catch (NoSuchMovieException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Film> create(Film film) {
        try {
            return Optional.of(bazaDanych.create(film));
        } catch (MoviesAlreadyExistsException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Film> edit(Film film) {
        try {
            return Optional.of(bazaDanych.update(film));
        } catch (NoSuchMovieException e) {
            return Optional.empty();
        }

    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        try {
            bazaDanych.deleteById(id);
            return Optional.of(Boolean.TRUE);
        } catch (NoSuchMovieException e) {
            return Optional.of(Boolean.FALSE);
        }
    }

    @Override
    public List<Film> findLatest3() {
        return Collections.emptyList();
    }

}
