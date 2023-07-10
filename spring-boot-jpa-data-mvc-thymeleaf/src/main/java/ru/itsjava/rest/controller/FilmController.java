package ru.itsjava.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itsjava.domain.Film;
import ru.itsjava.rest.dto.FilmDto;
import ru.itsjava.service.FilmService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;

    @GetMapping("/films")
    public String filmsPage(Model model) {
        List<Film> allFilms = filmService.getAllFilms();
        List<FilmDto> filmDtoList = new ArrayList<>();

        for (Film film : allFilms) {
            filmDtoList.add(FilmDto.toDto(film));
        }

        //Из Dto мы не получаем persistent entities в нашем случае:
//        List<Film> filmFromDtoList = new ArrayList<>();
//
//        for (FilmDto film : filmDtoList) {
//            filmFromDtoList.add(FilmDto.fromDto(film));
//        }


        model.addAttribute("films", filmDtoList);
        return "films-page";
    }

}
