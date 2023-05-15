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
	@Query("SELECT r FROM Reservation r WHERE r.date = :date AND r.terrain.id_terrain = :terrainId AND (r.status = 'en attente' OR r.status = 'acceptée')")
	Optional<List<Reservation>> findByDateAndTerrain(@Param("date") Date date, @Param("terrainId") Long terrainId);

	Optional<List<Reservation>> findAllByUserId(Long userId);

     
	@Query("SELECT r FROM Reservation r WHERE r.date = :date AND r.terrain.id_terrain = :terrainId AND (r.status = 'en attente' OR r.status = 'acceptée') ORDER BY STR_TO_DATE(r.Hdebut, '%H:%i')")
	Optional<List<Reservation>> findByDateAndTerrainAndStatus(@Param("date") Date date, @Param("terrainId") Long terrainId);
	
	@Query("SELECT r FROM Reservation r WHERE r.status = 'en attente' AND r.terrain.id_terrain = :terrainId  ")
	List<Reservation> consulterReservationEnattenteSelonTerrain (@Param("terrainId") Long terrainId );
	
	@Query("SELECT r FROM Reservation r WHERE r.status = 'acceptée' AND r.terrain.id_terrain = :terrainId  ")
	List<Reservation> consulterReservationAccepteeSelonTerrain (@Param("terrainId") Long terrainId );
	
	@Query("SELECT r FROM Reservation r WHERE r.status <> 'annulée' AND r.user.id = :userId ORDER BY r.date DESC")
	Optional<List<Reservation>> rechercherParStatus (@Param("userId") Long userId);
  
	@Query("SELECT r FROM Reservation r WHERE r.status = 'en attente'")
	List<Reservation> findReservationsEnAttente();
	


}
