package ru.itsjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itsjava.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
