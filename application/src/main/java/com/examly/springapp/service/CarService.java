package com.examly.springapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.examly.springapp.*;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public Optional<Car> getCarById(String id) {
        return carRepository.findById(id);
    }

    public List<Car> getAllCars() {
        return (List<Car>) carRepository.findAll();
    }

    public void deleteCarById(String id) {
        carRepository.deleteById(id);
    }
}
