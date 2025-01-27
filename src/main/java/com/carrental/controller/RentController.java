package com.carrental.controller;

import com.carrental.dto.PaymentInfo;
import com.carrental.dto.RentDto;
import com.carrental.dto.RentResponse;
import com.carrental.service.RentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/rent")
public class RentController {
    private final RentService rentService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentResponse> rentCar(@RequestBody RentDto rentDto){
        System.out.println(rentDto);

        Long reservationId = rentService.rentCar(rentDto);
        return ResponseEntity.ok(new RentResponse(reservationId));
    }
    @PostMapping("/payment-intent")
    public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentInfo paymentInfo) throws StripeException{
        PaymentIntent paymentIntent = rentService.createPaymentIntent(paymentInfo);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<>(paymentStr, HttpStatus.OK);
    }
}
