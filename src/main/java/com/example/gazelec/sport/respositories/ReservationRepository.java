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
	
	//Optional<List<Reservation>> findByDate(Date date);
	@Query("SELECT r FROM Reservation r WHERE r.date = :date AND r.terrain.id_terrain = :terrainId")
	Optional<List<Reservation>> findByDateAndTerrain(@Param("date") Date date, @Param("terrainId") Long terrainId);

	Optional<List<Reservation>> findAllByUserId(Long userId);
	@Query("SELECT r FROM Reservation r WHERE r.status <> 'annulée' AND r.user.id = :userId")
	Optional<List<Reservation>> findByStatus(@Param("userId") Long userId);
  
	@Query("SELECT r FROM Reservation r WHERE r.status = 'en attente'")
	List<Reservation> findReservationsEnAttente();
	

}
