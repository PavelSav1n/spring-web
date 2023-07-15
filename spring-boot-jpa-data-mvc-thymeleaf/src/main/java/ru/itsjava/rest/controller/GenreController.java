package ru.itsjava.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itsjava.domain.Genre;
import ru.itsjava.rest.dto.GenreDto;
import ru.itsjava.service.GenreService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;

    @GetMapping("/genre")
    public String genrePage(Model model) {

        List<Genre> allGenreList = genreService.getAllGenre();
        List<GenreDto> genreDtoList = new ArrayList<>();
        for (Genre elem : allGenreList) {
            genreDtoList.add(GenreDto.toDto(elem));
        }

        model.addAttribute("genreList", genreDtoList);

        return "genre-page";
    }

    @GetMapping("genre/{id}")
    public String getGenrePage(@PathVariable("id") long id, Model model) {
        model.addAttribute("genre", GenreDto.toDto(genreService.getById(id).get()));
        return "get-genre-page";
    }

    @GetMapping("genre/add")
    public String addGenrePage(){
        return "add-genre-page";
    }

    @PostMapping("genre/add")
    public String postGenrePage(GenreDto genreDto){
        genreService.create(GenreDto.fromDto(genreDto));
        return "redirect:/genre";
    }


}
