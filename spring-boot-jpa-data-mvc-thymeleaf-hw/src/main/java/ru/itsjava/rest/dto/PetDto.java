package ru.itsjava.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import ru.itsjava.domain.Pet;

@Data
@AllArgsConstructor
public class PetDto {
    private String id;
    private String species;
    private String user_id;
    private String user_name; // в PetController будем подставлять сюда реальное имя владельца по его id


    public static PetDto toDto(Pet pet) {
        String id = String.valueOf(pet.getId());
        String species = pet.getSpecies();
        String user_id = String.valueOf(pet.getUser_id());

        return new PetDto(id, species, user_id, null);
    }

    public static Pet fromDto(PetDto petDto) {
        if (petDto.id == null){
            petDto.id = "0";
        }
        long id = Long.parseLong(petDto.id);
        String species = petDto.species;
        long user_id = Long.parseLong(petDto.user_id);

        return new Pet(id, species, user_id);
    }


}
