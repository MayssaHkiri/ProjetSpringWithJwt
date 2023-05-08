package com.example.gazelec.sport.respositories;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.lang.Object ; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.gazelec.sport.models.Reservation;


public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	
	Optional<List<Reservation>> findByDate(Date date);
	Optional<List<Reservation>> findAllByUserId(Long userId);

}
