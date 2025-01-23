package com.carrental.service;

import com.carrental.entity.Reservation;
import com.carrental.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;


    public List<Reservation> getReservationByEmail(String email) {
        System.out.println(email);
        return reservationRepository.findByCustomerEmailOrderByStartDateDesc(email);
    }
}
