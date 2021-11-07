package pl.straszewski.carrental.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import pl.straszewski.carrental.CarRentalApplication;
import pl.straszewski.carrental.model.Car;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = CarRentalApplication.class)
@ComponentScan(basePackages = "pl.straszewski.carrental.")
class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

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
    void testFindAll_NonNullList_IfCarAdded() {

        //when
        List<Car> carDb= carRepository.findAll();
        //then
        assertNotNull(carDb);
    }

    @Test
    void testGetById_CarDetailsCorrect_IfCarExists() {

        //when
        Car dbCar = carRepository.findById(1L).get();
        //then
        assertNotNull(dbCar);
        assertAll(
                () -> assertEquals("Opel", dbCar.getBrand()),
                () -> assertEquals("Vectra", dbCar.getModel()),
                () -> assertEquals("PO12345", dbCar.getRegNumber()),
                () -> assertTrue(dbCar.getAvailStatus())
        );
    }

}