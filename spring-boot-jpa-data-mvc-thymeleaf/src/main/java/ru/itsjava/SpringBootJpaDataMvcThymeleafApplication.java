package ru.itsjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.h2.tools.Console;

import java.sql.SQLException;

@SpringBootApplication
public class SpringBootJpaDataMvcThymeleafApplication {

	public static void main(String[] args) throws SQLException {
		ConfigurableApplicationContext context = SpringApplication.run(SpringBootJpaDataMvcThymeleafApplication.class, args);

		Console.main(args);

	}

}
