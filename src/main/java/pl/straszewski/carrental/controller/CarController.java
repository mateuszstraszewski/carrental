package pl.straszewski.carrental.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.straszewski.carrental.model.Car;
import pl.straszewski.carrental.repository.CarRepository;
import pl.straszewski.carrental.service.CarService;

import java.util.List;
import java.util.Optional;

@RestController
public class CarController {


    private final CarRepository carRepository;
    private final CarService carService;

    public CarController(CarRepository carRepository, CarService carService) {
        this.carRepository = carRepository;
        this.carService = carService;
    }

    @PostMapping("/cars")
    public ResponseEntity<Car> save(@RequestBody Car car) {
        try {
            return new ResponseEntity<>(carRepository.save(car), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAllCars() {
        try {
            List<Car> list = carRepository.findAll();
            if (list.isEmpty() || list.size() == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getSingleCar(@PathVariable Long id) {

            Optional<Car> car = carRepository.findById(id);

            if (car.isPresent()) {
                return new ResponseEntity<>(car.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        @PutMapping("/cars/{id}")
        public ResponseEntity<Car> updateCar(@RequestBody Car car) {
            try {
                return new ResponseEntity<>(carRepository.save(car), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @DeleteMapping("/cars/{id}")
        public ResponseEntity<HttpStatus> deleteCar(@PathVariable Long id) {
            try {
                Optional<Car> car = carRepository.findById(id);
                if (car.isPresent()) {
                    carRepository.delete(car.get());
                }
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @PutMapping("/cars/rent/{id}")
        public ResponseEntity<Car> rentCar(@PathVariable Long id) {
            try {
                Optional<Car> car = carRepository.findById(id);
                if (car.isPresent()) {
                    carService.rentCar(car.get());
                }
                return new ResponseEntity<>(car.get(), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        }

        @PutMapping("/cars/return/{id}")
        public ResponseEntity<Car> returnCar(@PathVariable Long id) {
            try {
                Optional<Car> car = carRepository.findById(id);
                if (car.isPresent()) {
                    carService.returnCar(car.get());
                }
            return new ResponseEntity<>(car.get(), HttpStatus.OK);
        } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }

        }
}








