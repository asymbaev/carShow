package com.binary.carShow.web;

import com.binary.carShow.entity.Car;
import com.binary.carShow.service.CarService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Consists 2 annotations, called Response body and Controller
@RequestMapping("/api/v1/car")
public class CarController {
    // Json
    // JavaScript Object Notation

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }
    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getCars()
    {
        return new ResponseEntity<>(carService.getCars(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        return new ResponseEntity<>(carService.getCarById(id), HttpStatus.OK);
    }
    // Http method indicate the action of an api client
    @PostMapping("/add")
    public ResponseEntity<Car> addCar(@RequestBody Car car){
        return new ResponseEntity<>(carService.addCar(car), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Car> deleteCarById(@PathVariable Long id) {
        carService.deleteCarById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCarById(@PathVariable Long id, @RequestBody Car car) {
        return new ResponseEntity<>(carService.updateCarById(id, car), HttpStatus.ACCEPTED);
    }

    @GetMapping("/make/{make}")
    public ResponseEntity<List<Car>> getCarListByMake(@PathVariable String make) {
        return new ResponseEntity<>(carService.getCarByMake(make), HttpStatus.OK);
    }

}
