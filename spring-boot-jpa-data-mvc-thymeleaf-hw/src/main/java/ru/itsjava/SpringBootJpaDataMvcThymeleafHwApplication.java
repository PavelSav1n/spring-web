package ru.itsjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;
import ru.itsjava.service.PetService;
import ru.itsjava.service.UserService;
import org.h2.tools.Console;

import java.sql.SQLException;

@SpringBootApplication
public class SpringBootJpaDataMvcThymeleafHwApplication {

	public static void main(String[] args) throws SQLException {
		ConfigurableApplicationContext context = SpringApplication.run(SpringBootJpaDataMvcThymeleafHwApplication.class, args);


	}

}
