package ru.itsjava.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itsjava.domain.Notebook;
import ru.itsjava.service.NotebookService;

@RestController
@RequiredArgsConstructor
public class NotebookRestController {
    private final NotebookService notebookService;

    @GetMapping("/rest")
    // рест через RequestParam получает параметр и значение в адресной строке (например http:.../rest?id=1)
    // TODO: не понятно, отдаём мы тут страницу или что?
    // TODO: отвечаю: отдаём тут JSON (аннотация @RestController)
    // Отдаём application/json -- это неупорядоченное множество пар {"ключ":"значение"} (числа без кавычек)
    // {
    //  "ключ1":"значение",
    //  "ключ2":число,
    //  "inner_object": { "x":1, "y":2 },
    //  "inner_array": [2, 3, 4],
    //  ...
    //  "ключN":"значение"
    // }
    public Notebook get(@RequestParam(value = "id", defaultValue = "1") long id) {
        return notebookService.findById(id);

// General:

//        Request URL:        http://localhost:8080/rest?id=2
//        Request Method:     GET
//        Status Code:        200
//        Remote Address:     [::1]:8080
//        Referrer Policy:    strict-origin-when-cross-origin

// Response Headers:

//        Connection:         keep-alive
//        Content-Type:       application/json
//        Date:               Sat, 24 Jun 2023 13:31:51 GMT
//        Keep-Alive:         timeout=60
//        Transfer-Encoding:  chunked
    }
}


