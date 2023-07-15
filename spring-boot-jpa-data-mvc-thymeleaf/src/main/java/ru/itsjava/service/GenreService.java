package ru.itsjava.service;

import ru.itsjava.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    void changeGenreByName(String oldName, String newName);

    void printGenreByName(String name);

    List<Genre> getAllGenre();

    Genre create(Genre genre);

    Optional<Genre> getById(long id);

    Genre update(Genre genre);

    void deleteById(long id);

}
