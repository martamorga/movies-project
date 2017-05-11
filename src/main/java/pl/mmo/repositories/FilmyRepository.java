package pl.mmo.repositories;

import java.util.List;

import pl.mmo.entities.Film;


public interface FilmyRepository {
    Film create(Film film) throws MoviesAlreadyExistsException;
    Film readById(Long id) throws NoSuchMovieException;
    Film update(Film film) throws NoSuchMovieException;
    void deleteById(Long id) throws NoSuchMovieException;
    List<Film> findAll();
}