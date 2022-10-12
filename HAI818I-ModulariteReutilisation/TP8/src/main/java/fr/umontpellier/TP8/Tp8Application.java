package fr.umontpellier.TP8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("fr.umontpellier.TP8.models")
@EnableJpaRepositories("fr.umontpellier.TP8.repositories")
public class Tp8Application extends SpringBootServletInitializer
{
	public static void main(String[] args)
	{
		SpringApplication.run(Tp8Application.class, args);
	}
}
