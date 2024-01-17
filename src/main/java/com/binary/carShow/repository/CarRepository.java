package com.binary.carShow.repository;

import com.binary.carShow.entity.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    //CRUD

    // entity - class presents structure of a table
// Hibernate implementation of JPA
// Rest (Representation State Transfer)
    // is an architecture style for creating web services
    // six constraints
    // Stateless : The server does not hold any information about the client state
    // Client and server : The server
    // Cacheable : The server
    // Uniform Interface:
    // Layer System:
    // Code and Demand Optional
    // Controller Layer


    List<Car> getAllCarByMake(String make);

//    List<Car> findCarByMakeAndModelOrderByAsc(String make, String model);
}


