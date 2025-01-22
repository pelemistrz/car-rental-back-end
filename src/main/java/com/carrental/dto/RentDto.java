package com.carrental.dto;

import com.carrental.entity.Car;
import com.carrental.entity.Customer;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RentDto {
    private Customer customer;
    private Car car;
    private LocalDate receptionDate;
    private LocalDate returnDate;
}
