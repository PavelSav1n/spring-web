package ru.itsjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.h2.tools.Console;
import ru.itsjava.service.FilmService;
import ru.itsjava.domain.*;
import ru.itsjava.service.FilmServiceImpl;
import ru.itsjava.service.GenreService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringBootJpaDataMvcThymeleafApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootJpaDataMvcThymeleafApplication.class, args);

        FilmService filmService = context.getBean(FilmService.class);
        GenreService genreService = context.getBean(GenreService.class);

        Genre genre = genreService.getById(1).get(); // получаем жанр из персиста

        filmService.create(new Film(0L, "NewFilm", genre, null)); // создаём фильм

        filmService.printAllFilms(); // получаем ексепшн InvalidDataAccessApiUsageException: detached entity passed to persist: ru.itsjava.domain.Genre

    }

}
