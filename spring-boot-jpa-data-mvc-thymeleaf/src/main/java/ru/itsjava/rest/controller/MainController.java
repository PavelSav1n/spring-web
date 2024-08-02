package ru.itsjava.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itsjava.domain.Order;
import ru.itsjava.service.OrderService;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final OrderService orderService;

    @GetMapping("/")
    String getIndexPage() {
        System.out.println("Starting program");

        System.out.println("Printing all orders:");

        orderService.getAllOrders().forEach(System.out::println);
        Order order = orderService.getById(1).get();

        return "index";
    }
}

