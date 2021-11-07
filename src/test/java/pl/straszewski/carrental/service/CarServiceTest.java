package pl.straszewski.carrental.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import pl.straszewski.carrental.CarRentalApplication;
import pl.straszewski.carrental.model.Car;
import pl.straszewski.carrental.repository.CarRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = CarRentalApplication.class)
@ComponentScan(basePackages = "pl.straszewski.carrental.")
class CarServiceTest {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CarService carService;

    private Car car;

    @BeforeEach
    public void setup() {
        car = new Car();
        car.setBrand("Opel");
        car.setModel("Vectra");
        car.setRegNumber("PO12345");
        carRepository.save(car);
    }

    @Test
    void testRentCar_AvailStatusFalse_IfCarBeingRentedSuccess() {

        //given
        Car carBeingRented = carRepository.findById(1L).get();
        //when
        assertDoesNotThrow(() -> carService.rentCar(carBeingRented));
        //then
        assertEquals(false, carRepository.findById(1L).get().getAvailStatus());
    }

    @Test
    void testRentCar_ThrowException_IfCarBeingRentedIsUnavailable() {

        //given
        Car carBeingRented = carRepository.findById(1L).get();
        carBeingRented.setAvailStatus(false);
        //when&then
        assertThrows(Exception.class, () -> carService.rentCar(carBeingRented));

    }

    @Test
    void testReturnCar_AvailStatusTrue_IfCarBeingReturnedSuccess() {

        //given
        Car carBeingReturned = carRepository.findById(1L).get();
        carBeingReturned.setAvailStatus(false);
        //when
        assertDoesNotThrow(() -> carService.returnCar(carBeingReturned));
        //then
        assertEquals(true, carBeingReturned.getAvailStatus());
    }

    @Test
    void testReturnCar_ThrowException_IfCarBeingReturnedIsAvailable() {
        //given
        Car carBeingReturned = carRepository.findById(1L).get();
        carBeingReturned.setAvailStatus(true);
        //when&then
        assertThrows(Exception.class, () -> carService.returnCar(carBeingReturned));

    }
}