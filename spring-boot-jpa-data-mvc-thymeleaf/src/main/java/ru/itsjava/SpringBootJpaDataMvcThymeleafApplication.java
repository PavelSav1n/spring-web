package ru.itsjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.h2.tools.Console;
import ru.itsjava.service.FilmService;
import ru.itsjava.domain.*;
import ru.itsjava.service.FilmServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringBootJpaDataMvcThymeleafApplication {

    public static void main(String[] args) throws SQLException {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootJpaDataMvcThymeleafApplication.class, args);



        FilmService filmService = context.getBean(FilmService.class);

        // Демонстрация того, что fromDto возвращает нам изменённый объект с другими полями
        Place place = new Place(0L, "newPlace", 3L);
        List<Place> list = new ArrayList<>();
        list.add(place);
        filmService.create(new Film(0L, "NewFilm", new Genre(0L, "fantasy"), list));
        filmService.printAllFilms();

//		Console.main(args);

    }

}
