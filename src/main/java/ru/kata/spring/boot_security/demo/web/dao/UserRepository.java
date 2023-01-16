package ru.kata.spring.boot_security.demo.web.dao;

import org.springframework.data.repository.CrudRepository;
import ru.kata.spring.boot_security.demo.web.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

}
