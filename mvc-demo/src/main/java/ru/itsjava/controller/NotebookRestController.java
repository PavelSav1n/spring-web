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
    // рест через RequestParam получает json в адресной строке (например http:.../rest?id=1)
    // TODO: не понятно, отдаём мы тут страницу или что?
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

