package ru.itsjava.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.domain.Pet;
import ru.itsjava.repository.PetRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;

    @Transactional
    @Override
    public Pet create(Pet pet) {
        return petRepository.save(pet);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Pet> findById(long id) {
        return petRepository.findById(id);
    }

    @Override
    public Optional<Pet> findBySpecie(String specie) {
        return petRepository.findBySpecies(specie);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public void printAll() {
        for (Pet pet : petRepository.findAll()) {
            System.out.println(pet);
        }
    }

    @Transactional
    @Override
    public Pet update(Pet pet) {
        return petRepository.save(pet);
    }

    @Transactional
    @Override
    public void delete(Pet pet) {
        petRepository.delete(pet);
    }
}
