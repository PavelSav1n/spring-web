package ru.itsjava.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.domain.Film;
import ru.itsjava.repository.FilmRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository;

    @Transactional(readOnly = true)
    @Override
    public void printAllFilms() {
        List<Film> filmList = filmRepository.findAll();
        for (Film elem : filmList) {
            System.out.println(elem);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    @Transactional
    @Override
    public Film create(Film film) {
        return filmRepository.save(film);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Film> getById(long id) {
        return filmRepository.findById(id);
    }

    @Transactional
    @Override
    public Film update(Film film) {
        return filmRepository.save(film);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        filmRepository.deleteById(id);
    }

}
