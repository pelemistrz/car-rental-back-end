package com.carrental.controller;

import com.carrental.entity.Car;
import com.carrental.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping
    public List<Car> getAllCars() {

        return carService.getAllCars();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCar(@RequestBody Car car){
      System.out.println(car.toString());
        carService.createCar(car);
        return ResponseEntity.ok().build();
    }



    @GetMapping("/car-type/{carTypeId}")
    public Page<Car> getCarsByCarTypeId(@PathVariable("carTypeId") Long carTypeId, Pageable pageable){
        return carService.getCarsByCarTypeId(carTypeId,pageable);
    }

    @GetMapping("/{model}")
    public Page<Car> getCarsByCarModel(@PathVariable("model") String model,Pageable pageable){
        return carService.getCarsByModel(model,pageable);
    }
    @GetMapping("/car/{id}")
    public Car getCarById(@PathVariable("id") Long id){
        return carService.getCarById(id);
    }
}
