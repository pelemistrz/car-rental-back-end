package com.carrental.repository;

import com.carrental.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
@Transactional
public interface CarRepository extends JpaRepository<Car, Long> {
    Page<Car> findByCarTypeId(@Param("id") Long id, Pageable pageable);

}
