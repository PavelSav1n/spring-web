package ru.itsjava.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.itsjava.domain.Genre;

@Data
@AllArgsConstructor
public class GenreDto {
    private String id;
    private String name;

    public static GenreDto toDto(Genre genre) {
        return new GenreDto(String.valueOf(genre.getId()), genre.getName());
    }

    public static Genre fromDto(GenreDto genreDto) {
//        return new Genre(Long.parseLong(genreDto.getId()), genreDto.getName());
        if (genreDto.id == null) {
            genreDto.id = "0";
        }
        return new Genre(Long.parseLong(genreDto.id), genreDto.getName());
    }

}
