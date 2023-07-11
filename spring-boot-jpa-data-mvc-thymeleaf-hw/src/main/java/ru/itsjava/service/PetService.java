package ru.itsjava.service;

import ru.itsjava.domain.Pet;

import java.util.List;
import java.util.Optional;

public interface PetService {

    Pet create(Pet pet); // to add pet to persistent entities & DB

    Optional<Pet> findById(long id);

    List<Pet> findAll();

    void printAll();

    Pet update(Pet pet);

    void delete(Pet pet);
}
