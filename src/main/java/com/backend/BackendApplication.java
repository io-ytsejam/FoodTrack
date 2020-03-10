package main.java.com.backend;

import main.java.Models.*;
import org.springframework.boot.SpringApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BackendApplication {

	public static void main(String[] args) {
		//SpringApplication.run(BackendApplication.class, args);
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JavaHelps"); //name of persistence unit here
		EntityManager entityManager = factory.createEntityManager();


		entityManager.getTransaction().begin();

		// Here you can put database changes (Insert etc.)

		entityManager.getTransaction().commit();

		entityManager.close();
		factory.close();
	}

}

