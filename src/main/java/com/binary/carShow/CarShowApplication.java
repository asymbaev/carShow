package com.binary.carShow;

import com.binary.carShow.entity.Car;
import com.binary.carShow.entity.Owner;
import com.binary.carShow.repository.CarRepository;
import com.binary.carShow.repository.OwnerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;


//@EnableAutoConfiguration // This enables Spring Boot automatic configuration.Spring Boot will automatically
//configure your project based on the dependencies// For example, if you have the spring-boot-starter web dependency
//@ComponentScan // This enable the Spring Boot component scam to find all of the components in the application
//@Configuration // This annotation is used to define a configuration class that provides bean to the spring application content
@SpringBootApplication
public class CarShowApplication implements CommandLineRunner {
	@Autowired
	private CarRepository carRepository;

	@Autowired
	private OwnerRepository ownerRepository;
private static final Logger logger = LoggerFactory.getLogger(CarShowApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CarShowApplication.class, args);
		logger.info("Application started");
	}

	@Override
	public void run(String... args) throws Exception {
		Owner owner = new Owner("John", "Doe");
		Owner owner2 = new Owner("Dastan", "Smith");
		ownerRepository.save(owner);
		ownerRepository.save(owner2);

		List<Car> cars = Arrays.asList(
				new Car("Ford", "Lighting", "gray", "FL-234", 2023, 75000, owner),
				new Car("Nissan", "Rogue", "Silver", "Cl335", 2024, 34000, owner2)
		);
		carRepository.saveAll(cars);


		carRepository.findAll().forEach(car -> logger.info(car.getMake()+" "+car.getModel()));

		ownerRepository.findAll().forEach(ow -> logger.info(ow.getFirstName()));
	}

	// ORM (Object Relational Mapping) : is a technic that allows you to fetch from and manipulate Database
	// by using OOP paradigm
// class Book (id, title, author, price) -> Table book(id, title, author, price)
	//entity -> table
}
