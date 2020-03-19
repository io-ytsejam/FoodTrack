package com.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.backend.Models.*;
import com.backend.Controllers.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories
@ComponentScan("com.backend.Controllers")
public class BackendApplication {

	private static final Logger log = LoggerFactory.getLogger(BackendApplication.class);

	/*@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(JpaVendorAdapter adapter, DataSource ds) {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setPersistenceUnitName("JavaHelps");
		Map<String, String> properties = new HashMap<>();
		properties.put("javax.persistence.schema-generation.database.action", "drop-and-create");
		emf.setJpaPropertyMap(properties);
		emf.setDataSource(ds);
		emf.setJpaVendorAdapter(adapter);
		emf.setPackagesToScan("com.backend");
		return emf;
	}*/

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager txManager = new JpaTransactionManager(emf);
		return txManager;
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(BackendApplication.class, args);

		PersonEntity personEntity = new PersonEntity("czarek", "alala", "cezary", "wojcik");
		RecipeEntity recipeEntity= new RecipeEntity("kurczak", "alalal", 'N');
		IngredientEntity ingredientEntity=new IngredientEntity("pomidor");

		PersonRepo personRepo = ctx.getBean(PersonRepo.class);
		personRepo.save(personEntity);
		RecipeRepo recipeRepo = ctx.getBean(RecipeRepo.class);
		recipeRepo.save(recipeEntity);
		IngredientRepo ingredientRepo = ctx.getBean(IngredientRepo.class);
		ingredientRepo.save(ingredientEntity);


		ctx.close();
	}

}