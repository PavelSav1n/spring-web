package ru.itsjava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    // Можно писать двумя способами:
    //@RequestMapping(value = "/", method = RequestMethod.GET) // или:
    @GetMapping("/")
    public String getAll() {

        return "index";
    }

}
