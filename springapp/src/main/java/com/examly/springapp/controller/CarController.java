package com.examly.springapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("")
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/saveCar")
    @ResponseBody
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        Car savedCar = carService.saveCar(car);
        return ResponseEntity.ok(savedCar);
    }

    @PutMapping("/updateCar/{id}")
    @ResponseBody
    public ResponseEntity<Car> updateCar(@PathVariable String id, @RequestBody Car car) {
        Optional<Car> optionalCar = carService.getCarById(id);
        if (optionalCar.isPresent()) {
            Car existingCar = optionalCar.get();
            existingCar.setCarModel(car.getCarModel());
            existingCar.setCarNo(car.getCarNo());
            existingCar.setStatus(car.getStatus());
            carService.saveCar(existingCar);
            return new ResponseEntity<>(existingCar, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/deleteCar")
@ResponseBody
public ResponseEntity<?> deleteCar(@RequestParam("carId") String carId) {
    Optional<Car> optionalCar = carService.getCarById(carId);
    if (optionalCar.isPresent()) {
        carService.deleteCarById(carId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    return ResponseEntity.notFound().build();
}


    @GetMapping("/getCars")
    @ResponseBody
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.getAllCars();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

   @GetMapping("/getCar")
@ResponseBody
public ResponseEntity<Car> getCarById(@RequestParam(name = "carId", required = false) String carId,
                                      @PathVariable(name = "id", required = false) String pathId) {
    String id = carId != null ? carId : pathId;
    if (id != null) {
        Optional<Car> optionalCar = carService.getCarById(id);
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            return new ResponseEntity<>(car, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    } else {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

}
