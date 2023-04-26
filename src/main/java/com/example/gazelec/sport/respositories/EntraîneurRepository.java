package com.example.gazelec.sport.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.gazelec.sport.models.Entraîneur;
import com.example.gazelec.sport.models.User;

public interface EntraîneurRepository extends  JpaRepository <Entraîneur , Long > {
	Boolean existsByEmail(String email);
	@Query(value = "SELECT e.*, d.discipline AS nom_discipline FROM entraîneur e INNER JOIN discipline d ON e.Id_Discipline = d.Id_Discipline\r\n"
			+ "WHERE discipline =:disModerateur ",nativeQuery = true)
    public List<Entraîneur> ListeEntraineursetDisciplines(@Param(value= "disModerateur") String discipline);
	

	
	@Query(value = "SELECT e.*, d.discipline AS nom_discipline \r\n"
			+ "FROM entraîneur e \r\n"
			+ "INNER JOIN discipline d ON e.Id_Discipline = d.Id_Discipline\r\n"
			+ "WHERE (e.nom LIKE %:recherche% OR e.prenom LIKE %:recherche% OR e.email LIKE %:recherche%\r\n"
			+ "  OR e.adresse LIKE %:recherche%) and (discipline =:disModerateur)",nativeQuery = true)
    public List<Entraîneur> RechercherEntraineurs (@Param (value="recherche") String critere , @Param (value="disModerateur")String discipline ) ; 

}
