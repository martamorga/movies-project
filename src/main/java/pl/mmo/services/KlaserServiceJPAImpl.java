package pl.mmo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.mmo.entities.Film;
import pl.mmo.repositories.springdata.FilmRepostory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
@Qualifier("spring-data")
public class KlaserServiceJPAImpl implements KlaserService {

    @Autowired
    private FilmRepostory bazaDanych;

    @Override
    public List<Film> findAll() {
        List<Film> l = new ArrayList<>();
        for (Film item : bazaDanych.findAll()) {
            l.add(item);
        }
        return l;
    }

    @Override
    public List<Film> findAllToSell() {
        List<Film> l = new ArrayList<>();
        for (Film item : bazaDanych.findAll()) {
            l.add(item);
        }
        return l;
    }

    @Override
    public Optional<Film> findById(Long id) {
        return Optional.ofNullable(bazaDanych.findById(id));
    }

    @Override
    public Optional<Film> create(Film film) {
        return Optional.of(bazaDanych.save(film));
    }

    @Override
    public Optional<Film> edit(Film film) {
        return Optional.of(bazaDanych.save(film));
    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        bazaDanych.delete(id.intValue());
        return Optional.of(Boolean.TRUE);
    }

    @Override
    public List<Film> findLatest3() {
        return Collections.emptyList();
    }

}
