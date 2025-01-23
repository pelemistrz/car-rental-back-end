package com.carrental.controller;

import com.carrental.entity.Reservation;
import com.carrental.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping
    public List<Reservation> getReservationByEmail(@RequestParam("email") String email){
        log.info(email);
        return reservationService.getReservationByEmail(email);
    }
}
