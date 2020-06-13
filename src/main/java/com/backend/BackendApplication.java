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

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
}

