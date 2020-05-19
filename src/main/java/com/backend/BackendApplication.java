package com.backend;

import com.backend.Repositories.SettingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.backend.Models.*;
import com.backend.Controllers.*;

@SpringBootApplication
public class BackendApplication{

	private static final Logger log = LoggerFactory.getLogger(BackendApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);


	}
	@Bean
	public CommandLineRunner demo(SettingRepository repository) {
		return (args) -> {
			if(!repository.existsByNameIgnoreCase("privacy"))
				repository.save(new SettingEntity("privacy"));
		};}





		/*EntityManagerFactory factory = Persistence.createEntityManagerFactory("JavaHelps"); //name of persistence unit here
		EntityManager entityManager = factory.createEntityManager();
		PersonEntity person=new PersonEntity();
		person.setFirstname("Czarek");
		person.setLastname("Alalala");
		person.setNickname("czarus87");
		person.setPassword("test");

		entityManager.getTransaction().begin();

		entityManager.persist(person);
		// Here you can put database changes (Insert etc.)
		entityManager.getTransaction().commit();

		entityManager.close();
		factory.close();
	}*/

}

