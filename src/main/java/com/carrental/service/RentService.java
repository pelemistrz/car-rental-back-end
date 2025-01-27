package com.carrental.service;

import com.carrental.dto.PaymentInfo;
import com.carrental.dto.RentDto;
import com.carrental.entity.Car;
import com.carrental.entity.Customer;
import com.carrental.entity.Reservation;
import com.carrental.repository.CustomerRepository;
import com.carrental.repository.ReservationRepository;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import jakarta.transaction.Transactional;
import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service

public class RentService {
    private final CustomerRepository customerRepository;
    private final ReservationRepository reservationRepository;

    public RentService(CustomerRepository customerRepository, ReservationRepository reservationRepository,@Value("${stripe.key.secret}") String secretKey) {
        this.customerRepository = customerRepository;
        this.reservationRepository = reservationRepository;
        Stripe.apiKey = secretKey;
    }

    @Transactional
    public Long rentCar(RentDto rentDto){
        Car car = rentDto.getCar();
        LocalDate receptionDate = rentDto.getReceptionDate();
        LocalDate returnDate = rentDto.getReturnDate();

        Customer customer = null;

        Customer customerFromDb = customerRepository.findByEmail(rentDto.getCustomer().getEmail());
        if(customerFromDb!=null){
            customer = customerFromDb;
        } else {
            customer = new Customer(rentDto.getCustomer().getFirstName(),
                    rentDto.getCustomer().getFirstName(),
                    rentDto.getCustomer().getCountry(),
                    rentDto.getCustomer().getEmail());
            customerRepository.save(customer);
        }
        long howManyDays = ChronoUnit.DAYS.between(receptionDate,returnDate);
        BigDecimal totalFee =  car.getDailyFee().multiply(new BigDecimal(howManyDays));

        Reservation reservation = new Reservation();
        reservation.setTotalFee(totalFee);
        reservation.setCar(car);
        reservation.setCustomer(customer);
        reservation.setStartDate(receptionDate);
        reservation.setEndDate(returnDate);

        reservationRepository.save(reservation);
        return reservation.getId();
    }

    public PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws  StripeException {
        List<String> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");

        Map<String,Object> params = new HashMap<>();
        params.put("amount",paymentInfo.getAmount());
        params.put("currency",paymentInfo.getCurrency());
        params.put("payment_method_types",paymentMethodTypes);
        params.put("receipt_email",paymentInfo.getReceiptEmail());
        params.put("description","Car rental service");
        return PaymentIntent.create(params);
    }


}
