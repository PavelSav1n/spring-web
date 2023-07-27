package ru.itsjava.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.domain.Genre;
import ru.itsjava.repository.FilmRepository;
import ru.itsjava.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final FilmRepository filmRepository;

    @Transactional
    @Override
    public void changeGenreByName(String oldName, String newName) {
        Genre genreToBeUpdated = genreRepository.getByName(oldName).get();
        genreToBeUpdated.setName(newName);
        genreRepository.save(genreToBeUpdated);
    }

    @Transactional(readOnly = true)
    @Override
    public void printGenreByName(String genreName) {
        System.out.println(genreRepository.getByName(genreName).get());
    }

    @Override
    public List<Genre> getAllGenre() {
        return genreRepository.findAll();
    }

    @Transactional
    @Override
    public Genre create(Genre genre) {
        return genreRepository.save(genre);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Genre> getById(long id) {
        return genreRepository.findById(id);
    }

    @Override
    public Genre update(Genre genre) {
        return genreRepository.save(genre);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        filmRepository.deleteAllByGenreId(id);
        genreRepository.deleteById(id);
    }
}
