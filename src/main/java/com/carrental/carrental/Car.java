package com.carrental.carrental;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="cars")
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private BigDecimal dailyFee;
    private LocalDateTime date_created;
    private LocalDateTime last_updated;
    private String registration;
    private BigDecimal dailyPenalty;
    private BigDecimal fuelConsumption;
    private String typeOfFuel;

    @PrePersist
    void prePersist() {
        date_created = LocalDateTime.now();
    }
    @PreUpdate
    void preUpdate() {
        last_updated = LocalDateTime.now();
    }
}
