package ru.itsjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itsjava.domain.Film;

public interface FilmRepository extends JpaRepository<Film, Long> {
}
