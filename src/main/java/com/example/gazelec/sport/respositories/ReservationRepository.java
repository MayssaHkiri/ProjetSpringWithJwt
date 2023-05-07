package com.example.gazelec.sport.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gazelec.sport.models.Reservation;


public interface ReservationRepository extends JpaRepository<Reservation, Long>{

}
