package ru.itsjava.service;

import ru.itsjava.domain.Notebook;
public interface NotebookService {
    Notebook findById(long id);
}
