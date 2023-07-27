package ru.itsjava.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itsjava.domain.Film;
import ru.itsjava.rest.dto.FilmDto;
import ru.itsjava.service.FilmService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;

    @GetMapping("/film")
    public String filmsPage(Model model) {
        List<Film> allFilms = filmService.getAllFilms();
        List<FilmDto> filmDtoList = new ArrayList<>();

        for (Film film : allFilms) {
            filmDtoList.add(FilmDto.toDto(film));
        }

        model.addAttribute("films", filmDtoList);
        return "films-page";
    }

    @GetMapping("/film/{id}")
    public String getFilmPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("film", FilmDto.toDto(filmService.getById(id).get()));
        return "get-film-page";
    }

    @GetMapping("/film/add")
    public String addFilmPage() {
        return "add-film-page";
    }

    @PostMapping("/film/add")
    public String postFilmPage(FilmDto filmDto){
        filmService.create(FilmDto.fromDto(filmDto));
        return "/film";
    }
}
