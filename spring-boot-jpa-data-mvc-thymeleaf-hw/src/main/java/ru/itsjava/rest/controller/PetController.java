package ru.itsjava.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;
import ru.itsjava.rest.dto.PetDto;
import ru.itsjava.rest.dto.UserDto;
import ru.itsjava.service.PetService;
import ru.itsjava.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PetController {
    private final PetService petService;
    private final UserService userService;

    @GetMapping("/pets")
    public String getPetsPage(Model model) {
        // Переводим в DTO список всех питомцев:
        List<Pet> petList = petService.findAll();
        List<PetDto> petDtoList = new ArrayList<>();
        for (Pet pet : petList) {
            PetDto petDto = PetDto.toDto(pet);
            // В petDto сеттером устанавливаем имя владельца данного пета:
            petDto.setUser_name(userService.findById(pet.getUser_id()).get().getName());
            petDtoList.add(petDto);
        }
        // передаём в thymeleaf список всех питомцев, чтобы обработать их в pets-page.html
        model.addAttribute("pets", petDtoList);

        petDtoList.stream().forEach(x -> System.out.println(x)); // for debug

        return "pets-page";
    }

    @GetMapping("/pets/{id}")
    public String getPetPage(@PathVariable("id") long id, Model model) {
        if (petService.findById(id).isPresent()) {
            PetDto petDto = PetDto.toDto(petService.findById(id).get());
            // В petDto сеттером устанавливаем имя владельца данного пета:
            petDto.setUser_name(userService.findById(Long.parseLong(petDto.getUser_id())).get().getName());
            model.addAttribute("pet", petDto);
            return "get-pet-page";
        }
        return "404";
    }

    @GetMapping("pets/add")
    public String addPetPage(Model model) {
        // Передаём на страницу add-pet-page.html список пользователей для выбора владельца создаваемого пета.
        List<User> userList = userService.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            userDtoList.add(UserDto.toDto(user));
        }

        model.addAttribute("users", userDtoList);
        return "add-pet-page";
    }

    @PostMapping("pets/add")
    public String postPet(PetDto petDto) {
        System.out.println("pets/add -- petDto = " + petDto);
        petService.create(PetDto.fromDto(petDto));
        return "redirect:/pets";
    }

}
