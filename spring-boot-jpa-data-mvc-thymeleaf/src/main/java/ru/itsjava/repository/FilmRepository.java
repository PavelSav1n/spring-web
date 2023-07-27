package ru.itsjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itsjava.domain.Film;
import ru.itsjava.domain.Genre;

public interface FilmRepository extends JpaRepository<Film, Long> {
    void deleteAllByGenreId(long id);
}
