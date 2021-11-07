package pl.straszewski.carrental.controller;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.straszewski.carrental.model.Car;
import pl.straszewski.carrental.repository.CarRepository;
import pl.straszewski.carrental.service.CarService;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarRepository carRepository;

    @MockBean
    private CarService carService;

    @Test
    void testCarsEndpointGetMethod_StatusOK_IfListIsPopulated() throws Exception {

        Car car = new Car();
        car.setBrand("Opel");
        car.setModel("Vectra");
        car.setRegNumber("PO12345");
        doReturn(Lists.newArrayList(car)).when(carRepository).findAll();

        mockMvc.perform(MockMvcRequestBuilders
                .get("/cars"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetCarEndpoint_StatusNoContent_IfListIsEmpty() throws Exception {

        doReturn(Lists.newArrayList()).when(carRepository).findAll();
        mockMvc.perform(MockMvcRequestBuilders
                .get("/cars"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void testCarsEndpointGetSingleCarMethod_StatusOK_IfCarExists() throws Exception {

        Car car = new Car();
        car.setBrand("Opel");
        car.setModel("Vectra");
        car.setRegNumber("PO12345");
        doReturn(Optional.of(car)).when(carRepository).findById(anyLong());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/cars/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk());


    }
}