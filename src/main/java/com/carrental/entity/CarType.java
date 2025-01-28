package com.carrental.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="car_types")
@NoArgsConstructor
public class CarType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String carType;

    @Override
    public String toString() {
        return "CarType{" +
                "id=" + id +
                ", carType='" + carType + '\'' +
                '}';
    }
}
