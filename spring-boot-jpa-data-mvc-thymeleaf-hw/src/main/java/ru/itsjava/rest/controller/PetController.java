package ru.itsjava.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itsjava.domain.Pet;
import ru.itsjava.rest.dto.PetDto;
import ru.itsjava.service.PetService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PetController {
    private final PetService petService;

    @GetMapping("/pets")
    public String getPetPage(Model model) {

        List<Pet> petList = petService.findAll();
        List<PetDto> petDtoList = new ArrayList<>();
        for (Pet pet : petList) {
            petDtoList.add(PetDto.toDto(pet));
        }
        // передаём в thymeleaf список всех питомцев, чтобы обработать их в pets-page.html
        model.addAttribute("pets", petDtoList);

        return "pets-page";
    }

}
