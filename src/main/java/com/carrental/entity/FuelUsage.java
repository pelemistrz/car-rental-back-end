package com.carrental.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "fuel_usage")
public class FuelUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fuel_category_id")
    private FuelCategory fuelCategory;
    private int CounterReadingBegin;
    private int CounterReadingEnd;
    private BigDecimal CostOfFuel;
}