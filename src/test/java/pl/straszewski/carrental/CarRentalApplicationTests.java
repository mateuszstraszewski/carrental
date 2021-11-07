package pl.straszewski.carrental;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.straszewski.carrental.model.Car;
import pl.straszewski.carrental.repository.CarRepository;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
class CarRentalApplicationTests {

    @Autowired
    CarRepository carRepository;

    @Test
    public void testCreateCar() {
        Car car = new Car();
        car.setBrand("Opel");
        car.setModel("Vectra");
        car.setRegNumber("PO54321");
        carRepository.save(car);
        assertNotNull(carRepository.findById(1L).get());
    }

    @Test
    public void testGetAllCars() {
        List<Car> carList = carRepository.findAll();
        assertThat(carList).size().isGreaterThan(0);
    }

    @Test
    public void testGetSingleProduct() {
        Car car = carRepository.findById(1L).get();
        assertEquals("PO54321", car.getRegNumber());
    }


}
