package com.dalio.addressbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner loadData(CustomerRepository repository) {
		return (args) -> {
			// save a couple of customers
//			repository.save(new Customer("Jack", "Bauer","2345","daliborn@gmail.com"));
//			repository.save(new Customer("Chloe", "O'Brian","143413241234","daliborn@gmail.com"));
//			repository.save(new Customer("Kim", "Bauer","13413413","daliborn@gmail.com"));
//			repository.save(new Customer("David", "Palmer","3412341234","daliborn@gmail.com"));
//			repository.save(new Customer("Michelle", "Dessler","2134123412","daliborn@gmail.com"));
//
//			// fetch all customers
//			log.info("Customers found with findAll():");
//			log.info("-------------------------------");
//			for (Customer customer : repository.findAll()) {
//				log.info(customer.toString());
//			}
//			log.info("");
//
//			// fetch an individual customer by ID
//			Customer customer = repository.findOne(1L);
//			log.info("Customer found with findOne(1L):");
//			log.info("--------------------------------");
//			log.info(customer.toString());
//			log.info("");
//
//			// fetch customers by last name
//			log.info("Customer found with findByLastNameStartsWithIgnoreCase('Bauer'):");
//			log.info("--------------------------------------------");
//			for (Customer bauer : repository
//					.findByLastNameStartsWithIgnoreCase("Bauer")) {
//				log.info(bauer.toString());
//			}
//			log.info("");
		};
	}

}
