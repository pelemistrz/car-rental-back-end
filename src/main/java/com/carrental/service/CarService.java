package com.carrental.service;

import com.carrental.entity.Car;
import com.carrental.entity.CarType;
import com.carrental.repository.CarRepository;
import com.carrental.repository.CarTypeRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {


    private final CarRepository carRepository;
    private final CarTypeRepository carTypeRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public List<CarType> getAllCarTypes() {
        return carTypeRepository.findAll();
    }

    public Page<Car> getCarsByCarTypeId(Long carTypeId, Pageable pageable) {
        return carRepository.findByCarTypeId(carTypeId,pageable);
    }

    public Page<Car> getCarsByModel(String name, Pageable pageable) {
        return carRepository.findByModelContaining(name,pageable);
    }

    public Car getCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }
}
