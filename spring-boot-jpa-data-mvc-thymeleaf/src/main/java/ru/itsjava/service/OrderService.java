package ru.itsjava.service;

import ru.itsjava.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    void printAllOrders();

    List<Order> getAllOrders();

    Order create(Order order);

    Optional<Order> getById(long id);

    Order update(Order order);

    void deleteById(long id);

}
