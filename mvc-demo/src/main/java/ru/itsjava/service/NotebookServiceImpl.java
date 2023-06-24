package ru.itsjava.service;

import org.springframework.stereotype.Service;
import ru.itsjava.domain.Notebook;

@Service
public class NotebookServiceImpl implements NotebookService {
    @Override
    public Notebook findById(long id) {
        if (id == 1) return new Notebook("Apple");
        return new Notebook("Asus");
    }
}
