package com.backend;

import Models.PersonEntity;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@SpringBootApplication
public class BackendApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JavaHelps"); //name of persistence unit here
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
	}

}

