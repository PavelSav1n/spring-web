package ru.itsjava.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itsjava.domain.Notebook;
import ru.itsjava.service.NotebookService;

@Controller
@RequiredArgsConstructor
public class NotebookController {
    private final NotebookService notebookService;

    @GetMapping("/notebook/{id}") // получаем из адресной строки переменную id
    // передаём её внутрь метода через @PathVariable("имя_переменной_в_адресной_строке") тип имя_переменной +
    // Model model, для передачи в get-page.html аттрибутов
    public String getPage(@PathVariable("id") long id, Model model) {
        Notebook notebook = notebookService.findById(id);
        // передаём в get-page аттрибут с именем "notebook" (внутри html будут доступны методы notebook)
        model.addAttribute("notebook", notebook);
        System.out.println(notebook);
        return "get-page";

// General:

//        Request URL:        http://localhost:8080/notebook/2
//        Request Method:     GET
//        Status Code:        200
//        Remote Address:     [::1]:8080
//        Referrer Policy:    strict-origin-when-cross-origin

// Response Headers:

//        Connection:           keep-alive
//        Content-Language:     ru-RU
//        Content-Type:         text/html;charset=UTF-8
//        Date:                 Sat, 24 Jun 2023 13:24:49 GMT
//        Keep-Alive:           timeout=60
//        Transfer-Encoding:    chunked
    }
}
