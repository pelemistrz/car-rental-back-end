package com.carrental.service;

import com.carrental.dto.RentDto;
import com.carrental.entity.Car;
import com.carrental.entity.Customer;
import com.carrental.entity.Reservation;
import com.carrental.repository.CarRepository;
import com.carrental.repository.CustomerRepository;
import com.carrental.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class RentService {
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;
    private final ReservationRepository reservationRepository;

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
}
