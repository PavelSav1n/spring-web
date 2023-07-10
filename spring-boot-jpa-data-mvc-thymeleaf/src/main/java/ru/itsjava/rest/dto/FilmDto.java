package ru.itsjava.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.itsjava.domain.Film;
import ru.itsjava.domain.Genre;
import ru.itsjava.domain.Place;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class FilmDto {
    private String id;
    private String title;
    private String genre;
    private String places;

    // Dto и элементы в нём не являются частью сущностей, которыми мы управляем и реализация fromDto здесь не даёт нам сущности JPA
    public static Film fromDto(FilmDto filmDto) {
        long id = Long.parseLong(filmDto.id);
        String title = filmDto.title;
        Genre genre = new Genre(0L, filmDto.genre);

        String[] splitPlaces = filmDto.places.split(",");
        List<Place> placeList = new ArrayList<>();

        for (String place : splitPlaces) {
            placeList.add(new Place(0L, place, id));
        }

        return new Film(id, title, genre, placeList);
    }

    public static FilmDto toDto(Film film) {
        String id = String.valueOf(film.getId());
        String title = film.getTitle();
        String genre = film.getGenre().getName();

        StringBuilder stringBuilder = new StringBuilder();

        List<Place> filmPlaces = film.getPlaces();
        for (Place filmPlace : filmPlaces) {
            stringBuilder.append(filmPlace.getName()).append(",");
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        String places = stringBuilder.toString();

        return new FilmDto(id, title, genre, places);
    }
}
