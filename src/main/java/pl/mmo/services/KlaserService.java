package pl.mmo.services;

import java.util.List;
import java.util.Optional;

import pl.mmo.entities.Film;


public interface KlaserService {
    List<Film> findAll();

    List<Film> findAllToSell();

    Optional<Film> findById(Long id);

    Optional<Film> create(Film film);

    Optional<Film> edit(Film film);

    Optional<Boolean> deleteById(Long id);

    List<Film> findLatest3();
}
