package com.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.backend.Models.*;
import com.backend.Controllers.*;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories
@ComponentScan("com.backend.Controllers")
public class BackendApplication{

	private static final Logger log = LoggerFactory.getLogger(BackendApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
		/*@Bean
		public CommandLineRunner demo(PersonEntityRepository repository) {
			return (args) -> {
				// save a few customers
				repository.save(new PersonEntity("Jack", "Bauer"));
				repository.save(new PersonEntity("Chloe", "O'Brian"));
				repository.save(new PersonEntity("Kim", "Bauer"));
				repository.save(new PersonEntity("David", "Palmer"));
				repository.save(new PersonEntity("Michelle", "Dessler"));

				// fetch all customers
				log.info("Customers found with findAll():");
				log.info("-------------------------------");
				for (PersonEntity customer : repository.findAll()) {
					log.info(customer.toString());
				}
				log.info("");

				// fetch an individual customer by ID
				PersonEntity customer = repository.findByPersonid(1L);
				log.info("Customer found with findById(1L):");
				log.info("--------------------------------");
				log.info(customer.toString());
				log.info("");

				// fetch customers by last name
				log.info("Customer found with findByLastName('Bauer'):");
				log.info("--------------------------------------------");
				repository.findByLastname("Bauer").forEach(bauer -> {
					log.info(bauer.toString());
				});
				// for (Customer bauer : repository.findByLastName("Bauer")) {
				//  log.info(bauer.toString());
				// }
				log.info("");
			};
		}*/





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

