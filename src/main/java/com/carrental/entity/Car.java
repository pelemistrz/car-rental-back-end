package com.carrental.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    private String imageUrl;

    private LocalDateTime date_created;
    private LocalDateTime last_updated;


    @ManyToOne
    @JoinColumn(name="fuel_category_id")
    private FuelCategory fuelCategory;

    @ManyToOne
    @JoinColumn(name="car_type_id")
    private CarType carType;


    @PrePersist
    void prePersist() {
        date_created = LocalDateTime.now();
    }
    @PreUpdate
    void preUpdate() {
        last_updated = LocalDateTime.now();
    }
}
