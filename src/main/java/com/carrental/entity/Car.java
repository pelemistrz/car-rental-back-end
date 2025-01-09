package com.carrental.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name="cars")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private BigDecimal dailyFee;
    private String registration;
    private BigDecimal dailyPenalty;
    private BigDecimal fuelConsumption;
    private String typeOfFuel;
    private Audit audit = new Audit();

    @ManyToOne
    @JoinColumn(name="car_type_id")
    private CarType carType;

}
