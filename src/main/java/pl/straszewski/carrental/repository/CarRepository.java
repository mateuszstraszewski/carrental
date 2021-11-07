package pl.straszewski.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.straszewski.carrental.model.Car;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAll();
    Optional<Car> findById(Long aLong);
    Car save(Car entity);

}
