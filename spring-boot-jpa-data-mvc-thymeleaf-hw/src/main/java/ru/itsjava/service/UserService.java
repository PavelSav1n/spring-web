package ru.itsjava.service;

import ru.itsjava.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User create(User user);

    Optional<User> findById(long id);

    List<User> findAll();

    void printAll();

    User update(User user);

    void delete(User user);
}
