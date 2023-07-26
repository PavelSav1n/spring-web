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
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PetService petService;

    @GetMapping("/users")
    public String getUsersPage(Model model) {
        List<User> userList = userService.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            userDtoList.add(UserDto.toDto(user));
        }
        model.addAttribute("users", userDtoList);
        return "users-page";
    }


    @GetMapping("/users/{id}")
    public String getUserPage(@PathVariable("id") long id, Model model) {
        if (userService.findById(id).isPresent()) {
            User user = userService.findById(id).get();
            UserDto userDto = UserDto.toDto(user);
            System.out.println("user = " + user); // for debug
            System.out.println("userDto = " + userDto); // for debug
            model.addAttribute("user", userDto);
            return "get-user-page";
        }
        return "404";
    }

    @GetMapping("/users/add")
    public String addUserPage(Model model) {
        // Передаём на страницу список петов для выбора при создании нового пользователя:
        List<Pet> petList = petService.findAll();
        List<PetDto> petDtoList = new ArrayList<>();
        for (Pet pet : petList) {
            petDtoList.add(PetDto.toDto(pet));
        }
        model.addAttribute("pets", petDtoList);
        return "add-user-page";
    }

    @PostMapping("/users/add")
    public String postUser(UserDto userDto) {
        System.out.println("userDto = " + userDto); // for debug
        System.out.println("UserDto.fromDto(userDto) = " + UserDto.fromDto(userDto));
        // На данном этапе наш пользователь выглядит типа User(id=0, name=Whatever, age=123, pets=[Pet(id=0, species=, user_id=0), Pet(id=0, species=cat, user_id=0), Pet(id=0, species=pig, user_id=0)])
        // Поскольку это новый пользователь, без id в бд, мы не можем создать ему валидный список животных (потому что в сущностях Pet должен быть id юзера)
        // Поэтому сначала создаём пользователя в JPA без животных, получаем его id, создаём список животных с корректным id и сохраняем нашего пользователя.
        User userFromDto = UserDto.fromDto(userDto); // сырой дтошный юзер
        User userFromJpa = userService.create(new User(0L, userFromDto.getName(), userFromDto.getAge())); // получаем из бд уже персистного юзера
        List<Pet> petListFromDto = userFromDto.getPets(); // из этого списка будем делать нормальный
        List<Pet> petListCorrect = new ArrayList<>(); // нормальный список
        for (Pet pet : petListFromDto) {
            if (!pet.getSpecies().isBlank()) { // проверка на пустое поле
                // Логика такая, что мы не плодим сущностей петов. Если мы выбрали для нового пользователя пета, который уже кому-то принадлежит, то этот пет меняет владельца.
                // Если мы хотим создавать каждому новому пользователю новых петов, то просто убираем petFromJpa и if ниже:
                Optional<Pet> petFromJpa = petService.findBySpecie(pet.getSpecies());
                if (petFromJpa.isPresent()) {
                    petListCorrect.add(new Pet(petFromJpa.get().getId(), pet.getSpecies(), userFromJpa.getId()));
                } else {
                    petListCorrect.add(new Pet(0L, pet.getSpecies(), userFromJpa.getId()));
                }
            }
        }

        if (petListCorrect.size() > 0) { // если что-то в списке есть, апдейтим. Если юзер был без пета, то он уже создан.
            userService.update(new User(userFromJpa.getId(), userFromJpa.getName(), userFromJpa.getAge(), petListCorrect));
        }

//        userService.create(UserDto.fromDto(userDto));
        return "redirect:/users";
    }


}
