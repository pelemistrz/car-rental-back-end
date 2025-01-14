package com.carrental.controller;

import com.carrental.entity.Car;
import com.carrental.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/car-type/{carTypeId}")
    public Page<Car> getCarsByCarTypeId(@PathVariable("carTypeId") Long carTypeId, Pageable pageable){
        return carService.getCarsByCarTypeId(carTypeId,pageable);
    }

    @GetMapping("/car/{name}")
    public Page<Car> getCarsByCarModel(@PathVariable("name") String name,Pageable pageable){
        return carService.getCarsByModel(name,pageable);
    }
}
