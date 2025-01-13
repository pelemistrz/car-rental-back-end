package com.carrental.repository;

import com.carrental.entity.CarType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CarTypeRepository extends JpaRepository<CarType,Long> {


}
