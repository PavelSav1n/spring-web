package ru.itsjava.service;

import ru.itsjava.domain.Film;

import java.util.List;
import java.util.Optional;

public interface FilmService {

    void printAllFilms();

    List<Film> getAllFilms();

    Film create(Film film);

    Optional<Film> getById(long id);

    Film update(Film film);

    void deleteById(long id);




}
