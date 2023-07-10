package ru.itsjava.service;

import ru.itsjava.domain.Film;

import java.util.List;

public interface FilmService {

    void printAllFilms();

    List<Film> getAllFilms();
}
