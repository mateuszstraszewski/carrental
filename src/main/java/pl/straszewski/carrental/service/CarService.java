package pl.straszewski.carrental.service;

import org.springframework.stereotype.Service;
import pl.straszewski.carrental.model.Car;
import pl.straszewski.carrental.repository.CarRepository;

@Service
public class CarService {

    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void rentCar(Car car) throws Exception {
        if (car.getAvailStatus() == false) {
            throw new Exception("Car with reg number " + car.getRegNumber() + " is already reserved!");
        }

        car.setAvailStatus(false);
        carRepository.save(car);
    }

    public void returnCar(Car car) throws Exception {
        if (car.getAvailStatus() == true) {
            throw new Exception("Car return not available!");
        }
        car.setAvailStatus(true);
        carRepository.save(car);
    }
}
