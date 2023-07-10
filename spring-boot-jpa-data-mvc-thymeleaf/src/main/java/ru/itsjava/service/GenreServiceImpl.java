package ru.itsjava.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.domain.Genre;
import ru.itsjava.repository.GenreRepository;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

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
}
