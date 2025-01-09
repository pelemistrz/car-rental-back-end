package com.carrental.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name="reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Audit audit=new Audit();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="car_id")
    private Car car;

    private LocalDate startDate;
    private LocalDate endDate;

    @OneToOne
    @JoinColumn(name="fuel_usage_id")
    private FuelUsage fuelUsage;









}
