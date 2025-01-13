package com.carrental.controller;

import com.carrental.entity.CarType;
import com.carrental.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/car-type")
@RequiredArgsConstructor
public class CarTypeController {
    private final CarService carService;

    @GetMapping
    public List<CarType> getAllCarTypes(){
        return carService.getAllCarTypes();
    }
}
