package ru.itsjava.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itsjava.domain.Genre;
import ru.itsjava.rest.dto.GenreDto;
import ru.itsjava.service.FilmService;
import ru.itsjava.service.GenreService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;

    @GetMapping("/genre")
    public String genrePage(Model model) {
        // Можно код ниже заменить стримом:
//        List<Genre> allGenreList = genreService.getAllGenre();
//        List<GenreDto> genreDtoList = new ArrayList<>();
//        for (Genre elem : allGenreList) {
//            genreDtoList.add(GenreDto.toDto(elem));
//        }
        // Вот так:
        List<GenreDto> genreDtoList = genreService.getAllGenre().stream().map(GenreDto::toDto).collect(Collectors.toList());

        model.addAttribute("genreList", genreDtoList);

        return "genre-page";
    }

    @GetMapping("genre/{id}")
    public String getGenrePage(@PathVariable("id") long id, Model model) {
        model.addAttribute("genre", GenreDto.toDto(genreService.getById(id).get()));
        return "get-genre-page";
    }

    @GetMapping("genre/add")
    public String addGenrePage() {
        return "add-genre-page";
    }

    @PostMapping("genre/add")
    public String postAddGenrePage(GenreDto genreDto) {
        genreService.create(GenreDto.fromDto(genreDto));
        return "redirect:/genre";
    }

    @GetMapping("genre/{id}/edit")
    public String editGenrePage(@PathVariable("id") long id, Model model) {
        Optional<Genre> genre = genreService.getById(id);
        if (genre.isPresent()) {
            model.addAttribute("genreDto", GenreDto.toDto(genre.get()));
            return "edit-genre-page";
        }
        return "404";
    }

    @PostMapping("genre/{id}/edit")
    public String postEditGenrePage(GenreDto genreDto) {
        genreService.update(GenreDto.fromDto(genreDto));
        return "redirect:/genre";
    }

    @GetMapping("genre/{id}/delete")
    public String deleteGenrePage(@PathVariable("id") long id, Model model) {
        Optional<Genre> genre = genreService.getById(id);
        if (genre.isPresent()) {
            model.addAttribute("genreDto", GenreDto.toDto(genre.get()));
            return "delete-genre-page";
        }
        return "404";
    }

    @PostMapping("genre/{id}/delete")
    public String postDeleteGenrePage(GenreDto genreDto) {
        // Поскольку у нас есть foreign key в фильмах по полю id жаров, то так просто жанр не удалить.
        // Поэтому можно удалить сначала фильм, а потом жанр:

        genreService.deleteById(GenreDto.fromDto(genreDto).getId());
        return "redirect:/genre";
    }

}
