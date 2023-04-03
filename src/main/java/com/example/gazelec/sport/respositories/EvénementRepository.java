package com.example.gazelec.sport.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.gazelec.sport.models.Evénement;




public interface EvénementRepository  extends JpaRepository < Evénement  , Long>   {
 
	/*@Query(value = "SELECT * FROM evenement e   WHERE  e.titre LIKE %:recherche% OR e.description LIKE %:recherche% OR e.lieu LIKE %:recherche% ) ",nativeQuery = true)
	public List<Evénement> RechercherEvent(@Param(value = "recherche") String critere);*/
	@Query(value = "SELECT * FROM evenement e WHERE e.titre LIKE %:recherche% OR e.description LIKE %:recherche% OR e.lieu LIKE %:recherche%", nativeQuery = true)
	public List<Evénement> RechercherEvent(@Param(value = "recherche") String critere);

}
