package ru.itsjava.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@AllArgsConstructor
public class UserDto {
    private String id;
    private String name;
    private String age;
    private String pets;

    public static UserDto toDto(User user) {
        String id = String.valueOf(user.getId());
        String name = user.getName();
        String age = String.valueOf(user.getAge());
        String pets = null;
        if (user.getPets().size() != 0) {
            StringBuilder petsNameString = new StringBuilder();
            for (Pet pet : user.getPets()) {
                petsNameString.append(pet.getSpecies()).append(", ");
            }
            petsNameString.deleteCharAt(petsNameString.length() - 2);

            pets = petsNameString.toString();
        }

        return new UserDto(id, name, age, pets);
    }

    public static User fromDto(UserDto userDto) {
        if (userDto.id == null){
            userDto.id = "0";
        }
        long id = Long.parseLong(userDto.id);
        String name = userDto.name;
        int age = Integer.parseInt(userDto.age);

        List<Pet> petList = new ArrayList<>();

        System.out.println("fromDto ********** userDto.pets = " + userDto.pets);

        if (userDto.pets != null) {
            String[] petsNamesArray = userDto.pets.split(",");
            for (String elem : petsNamesArray) {
                petList.add(new Pet(0L, elem, id));
            }
        }
        return new User(id, name, age, petList);
    }
}


