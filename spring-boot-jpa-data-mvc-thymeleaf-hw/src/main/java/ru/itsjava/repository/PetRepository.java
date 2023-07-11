package ru.itsjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itsjava.domain.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
