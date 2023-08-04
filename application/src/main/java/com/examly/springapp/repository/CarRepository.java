package com.examly.springapp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.examly.springapp.*;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {
}

