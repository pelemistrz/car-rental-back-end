package com.carrental.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date_created;
    private LocalDateTime last_updated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="car_id")
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id")
    private Customer customer;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @OneToOne
    @JoinColumn(name="fuel_usage_id")
    private FuelUsage fuelUsage;

    @PrePersist
    void prePersist() {
        date_created = LocalDateTime.now();
    }
    @PreUpdate
    void preUpdate() {
        last_updated = LocalDateTime.now();
    }







}
