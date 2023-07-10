package ru.itsjava.service;

public interface GenreService {

    void changeGenreByName(String oldName, String newName);

    void printGenreByName(String name);

}
