package com.example.gazelec.sport.respositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.gazelec.sport.models.Discipline;
import com.example.gazelec.sport.models.Inscription;
import com.example.gazelec.sport.models.User;

public interface InscriptionRepository extends JpaRepository < Inscription  , Long> {
	
	@Query(value = "SELECT \r\n"
			+ "  i.id_inscription AS id_inscription,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.nom \r\n"
			+ "    ELSE m.nom \r\n"
			+ "  END AS nom,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.prenom \r\n"
			+ "    ELSE m.prenom \r\n"
			+ "  END AS prenom,\r\n"
			+ " \r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.email \r\n"
			+ "    ELSE m.email \r\n"
			+ "  END AS email,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.telephone \r\n"
			+ "    ELSE m.telephone \r\n"
			+ "  END AS telephone,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.adresse \r\n"
			+ "    ELSE m.adresse \r\n"
			+ "  END AS adresse,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.lieu_naissance \r\n"
			+ "    ELSE m.lieu_naissance \r\n"
			+ "  END AS lieu_naissance,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.date_naissance \r\n"
			+ "    ELSE m.date_naissance \r\n"
			+ "  END AS date_naissance,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.type \r\n"
			+ "    ELSE mf.type \r\n"
			+ "  END AS type,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.nom \r\n"
			+ "    ELSE mf.nom \r\n"
			+ "  END AS nomAdherent,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.prenom \r\n"
			+ "    ELSE mf.prenom \r\n"
			+ "  END AS prenomAdherent,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.profession \r\n"
			+ "    ELSE mf.profession \r\n"
			+ "  END AS profession,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.matricule \r\n"
			+ "    ELSE mf.matricule \r\n"
			+ "  END AS matricule,\r\n"
			+ "  i.date_inscription,\r\n"
			+ "  i.mode_paiement,\r\n"
			+ "  m.id_membre_famille AS id_membre_famille,\r\n"
			+ "  m.relation AS relation,\r\n"
			+ "  d.discipline AS discipline\r\n"
			+ "FROM inscription i\r\n"
			+ "LEFT JOIN utilisateur u ON i.id_user = u.id\r\n"
			+ "LEFT JOIN membre_famille m ON i.id_membre_famille = m.id_membre_famille\r\n"
			+ "LEFT JOIN utilisateur mf ON m.id_user = mf.id\r\n"
			+ "JOIN discipline d ON i.id_discipline = d.id_discipline\r\n"
			+" WHERE i.status='en attente'"
			+ "ORDER BY i.date_inscription DESC "
			,nativeQuery = true)
	List<Object[]> DemandeInscription();
	

	@Query(value ="SELECT \r\n"
			+ "  i.id_inscription AS id_inscription,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.nom \r\n"
			+ "    ELSE m.nom \r\n"
			+ "  END AS nom,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.prenom \r\n"
			+ "    ELSE m.prenom \r\n"
			+ "  END AS prenom,\r\n"
			+ " \r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.email \r\n"
			+ "    ELSE m.email \r\n"
			+ "  END AS email,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.telephone \r\n"
			+ "    ELSE m.telephone \r\n"
			+ "  END AS telephone,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.adresse \r\n"
			+ "    ELSE m.adresse \r\n"
			+ "  END AS adresse,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.lieu_naissance \r\n"
			+ "    ELSE m.lieu_naissance \r\n"
			+ "  END AS lieu_naissance,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.date_naissance \r\n"
			+ "    ELSE m.date_naissance \r\n"
			+ "  END AS date_naissance,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.type \r\n"
			+ "    ELSE mf.type \r\n"
			+ "  END AS type,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.nom \r\n"
			+ "    ELSE mf.nom \r\n"
			+ "  END AS nomAdherent,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.prenom \r\n"
			+ "    ELSE mf.prenom \r\n"
			+ "  END AS prenomAdherent,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.profession \r\n"
			+ "    ELSE mf.profession \r\n"
			+ "  END AS profession,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.matricule \r\n"
			+ "    ELSE mf.matricule \r\n"
			+ "  END AS matricule,\r\n"
			+ "  i.date_inscription,\r\n"
			+ "  i.mode_paiement,\r\n"
			+ "  m.id_membre_famille AS id_membre_famille,\r\n"
			+ "  m.relation AS relation,\r\n"
			+ "  d.discipline AS discipline\r\n"
			+ "FROM inscription i\r\n"
			+ "LEFT JOIN utilisateur u ON i.id_user = u.id\r\n"
			+ "LEFT JOIN membre_famille m ON i.id_membre_famille = m.id_membre_famille\r\n"
			+ "LEFT JOIN utilisateur mf ON m.id_user = mf.id\r\n"
			+ "JOIN discipline d ON i.id_discipline = d.id_discipline\r\n"
			+ "WHERE (m.nom LIKE %:recherche% OR m.prenom LIKE %:recherche% OR u.nom LIKE %:recherche% OR u.prenom LIKE %:recherche% OR u.email LIKE %:recherche% OR m.email LIKE %:recherche% OR u.type LIKE %:recherche% OR mf.nom LIKE %:recherche% OR discipline LIKE %:recherche%)and (i.status='en attente')"
			+ "ORDER BY i.date_inscription DESC " ,nativeQuery = true)
	List<Object[]> RechercheInscription(@Param(value = "recherche") String recherche);

	
	@Query(value = "SELECT \r\n"
			+ "  i.id_inscription AS id_inscription,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.nom \r\n"
			+ "    ELSE m.nom \r\n"
			+ "  END AS nom,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.prenom \r\n"
			+ "    ELSE m.prenom \r\n"
			+ "  END AS prenom,\r\n"
			+ " \r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.email \r\n"
			+ "    ELSE m.email \r\n"
			+ "  END AS email,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.telephone \r\n"
			+ "    ELSE m.telephone \r\n"
			+ "  END AS telephone,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.adresse \r\n"
			+ "    ELSE m.adresse \r\n"
			+ "  END AS adresse,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.lieu_naissance \r\n"
			+ "    ELSE m.lieu_naissance \r\n"
			+ "  END AS lieu_naissance,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.date_naissance \r\n"
			+ "    ELSE m.date_naissance \r\n"
			+ "  END AS date_naissance,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.type \r\n"
			+ "    ELSE mf.type \r\n"
			+ "  END AS type,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.nom \r\n"
			+ "    ELSE mf.nom \r\n"
			+ "  END AS nomAdherent,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.prenom \r\n"
			+ "    ELSE mf.prenom \r\n"
			+ "  END AS prenomAdherent,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.profession \r\n"
			+ "    ELSE mf.profession \r\n"
			+ "  END AS profession,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.matricule \r\n"
			+ "    ELSE mf.matricule \r\n"
			+ "  END AS matricule,\r\n"
			+ "  i.date_inscription,\r\n"
			+ "  i.mode_paiement,\r\n"
			+ "  m.id_membre_famille AS id_membre_famille,\r\n"
			+ "  m.relation AS relation,\r\n"
			+ "  d.discipline AS discipline\r\n"
			+ "FROM inscription i\r\n"
			+ "LEFT JOIN utilisateur u ON i.id_user = u.id\r\n"
			+ "LEFT JOIN membre_famille m ON i.id_membre_famille = m.id_membre_famille\r\n"
			+ "LEFT JOIN utilisateur mf ON m.id_user = mf.id\r\n"
			+ "JOIN discipline d ON i.id_discipline = d.id_discipline\r\n"
			+" WHERE i.status='accepte'"
			+ "ORDER BY i.date_inscription DESC "
			,nativeQuery = true)
	List<Object[]> ListeDesmembres();
	
	@Query(value = "SELECT \r\n"
			+ "  i.id_inscription AS id_inscription,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.nom \r\n"
			+ "    ELSE m.nom \r\n"
			+ "  END AS nom,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.prenom \r\n"
			+ "    ELSE m.prenom \r\n"
			+ "  END AS prenom,\r\n"
			+ " \r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.email \r\n"
			+ "    ELSE m.email \r\n"
			+ "  END AS email,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.telephone \r\n"
			+ "    ELSE m.telephone \r\n"
			+ "  END AS telephone,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.adresse \r\n"
			+ "    ELSE m.adresse \r\n"
			+ "  END AS adresse,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.lieu_naissance \r\n"
			+ "    ELSE m.lieu_naissance \r\n"
			+ "  END AS lieu_naissance,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.date_naissance \r\n"
			+ "    ELSE m.date_naissance \r\n"
			+ "  END AS date_naissance,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.type \r\n"
			+ "    ELSE mf.type \r\n"
			+ "  END AS type,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.nom \r\n"
			+ "    ELSE mf.nom \r\n"
			+ "  END AS nomAdherent,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.prenom \r\n"
			+ "    ELSE mf.prenom \r\n"
			+ "  END AS prenomAdherent,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.profession \r\n"
			+ "    ELSE mf.profession \r\n"
			+ "  END AS profession,\r\n"
			+ "  CASE \r\n"
			+ "    WHEN i.id_user IS NOT NULL THEN u.matricule \r\n"
			+ "    ELSE mf.matricule \r\n"
			+ "  END AS matricule,\r\n"
			+ "  i.date_inscription,\r\n"
			+ "  i.mode_paiement,\r\n"
			+ "  m.id_membre_famille AS id_membre_famille,\r\n"
			+ "  m.relation AS relation,\r\n"
			+ "  d.discipline AS discipline\r\n"
			+ "FROM inscription i\r\n"
			+ "LEFT JOIN utilisateur u ON i.id_user = u.id\r\n"
			+ "LEFT JOIN membre_famille m ON i.id_membre_famille = m.id_membre_famille\r\n"
			+ "LEFT JOIN utilisateur mf ON m.id_user = mf.id\r\n"
			+ "JOIN discipline d ON i.id_discipline = d.id_discipline\r\n"
			+ "WHERE (m.nom LIKE %:recherche% OR m.prenom LIKE %:recherche% OR u.nom LIKE %:recherche% OR u.prenom LIKE %:recherche% OR u.email LIKE %:recherche% OR m.email LIKE %:recherche% OR u.type LIKE %:recherche% OR mf.nom LIKE %:recherche% OR discipline LIKE %:recherche%)and (i.status='accepte')"
			+ "ORDER BY i.date_inscription DESC "
			,nativeQuery = true)
	List<Object[]> RechercherMembre(@Param(value = "recherche") String recherche);

	
	
	
	
	
	
	@Query(value = "SELECT \r\n"


	+ " i.id_inscription AS id_inscription,\r\n"

	+ " CASE \r\n"

	+ " WHEN i.id_user IS NOT NULL THEN u.nom \r\n"

	+ " ELSE m.nom \r\n"

	+ " END AS nom,\r\n"

	+ " CASE \r\n"

	+ " WHEN i.id_user IS NOT NULL THEN u.prenom \r\n"

	+ " ELSE m.prenom \r\n"

	+ " END AS prenom,\r\n"

	+ " CASE \r\n"

	+ " WHEN i.id_user IS NOT NULL THEN u.email \r\n"

	+ " ELSE m.email \r\n"

	+ " END AS email,\r\n"

	+ " CASE \r\n"

	+ " WHEN i.id_user IS NOT NULL THEN u.date_naissance \r\n"

	+ " ELSE m.date_naissance \r\n"

	+ " END AS date_naissance,\r\n"

	+ " CASE \r\n"

	+ " WHEN i.id_user IS NOT NULL THEN u.type \r\n"

	+ " ELSE mf.type \r\n"

	+ " END AS type,\r\n"

	+ " CASE \r\n"

	+ " WHEN i.id_user IS NOT NULL THEN u.matricule \r\n"

	+ " ELSE mf.matricule \r\n"

	+ " END AS matricule,\r\n"
	
	+ " CASE \r\n"
	
	+ " WHEN i.id_user IS NOT NULL THEN u.adresse \r\n"

	+ " ELSE m.adresse \r\n"

	+ " END AS adresse,\r\n"
	
    + " CASE \r\n"
	
	+ " WHEN i.id_user IS NOT NULL THEN u.telephone \r\n"

	+ " ELSE m.telephone \r\n"

	+ " END AS telephone,\r\n"


	+ " i.date_inscription,\r\n"

	+ " m.id_membre_famille AS id_membre_famille,\r\n"

	+ " d.discipline AS discipline\r\n"

	+ "FROM inscription i\r\n"

	+ "LEFT JOIN utilisateur u ON i.id_user = u.id\r\n"

	+ "LEFT JOIN membre_famille m ON i.id_membre_famille = m.id_membre_famille\r\n"

	+ "LEFT JOIN utilisateur mf ON m.id_user = mf.id\r\n"

	+ "JOIN discipline d ON i.id_discipline = d.id_discipline \r\n"

	+ "WHERE (discipline =:disModerateur) and (i.status='accepte')"

	+ "ORDER BY i.date_inscription DESC ",nativeQuery = true)

	List<Object[]> ConsulterMembresByModerateur(@Param(value = "disModerateur") String discipline );
	
	
	@Query(value = "SELECT \r\n"

	+ " i.id_inscription AS id_inscription,\r\n"

	+ " CASE \r\n"

	+ " WHEN i.id_user IS NOT NULL THEN u.nom \r\n"

	+ " ELSE m.nom \r\n"

	+ " END AS nom,\r\n"

	+ " CASE \r\n"

	+ " WHEN i.id_user IS NOT NULL THEN u.prenom \r\n"

	+ " ELSE m.prenom \r\n"

	+ " END AS prenom,\r\n"

	+ " CASE \r\n"

	+ " WHEN i.id_user IS NOT NULL THEN u.email \r\n"

	+ " ELSE m.email \r\n"

	+ " END AS email,\r\n"

	+ " CASE \r\n"

	+ " WHEN i.id_user IS NOT NULL THEN u.date_naissance \r\n"

	+ " ELSE m.date_naissance \r\n"

	+ " END AS date_naissance,\r\n"

	+ " CASE \r\n"

	+ " WHEN i.id_user IS NOT NULL THEN u.type \r\n"

	+ " ELSE mf.type \r\n"

	+ " END AS type,\r\n"

	+ " CASE \r\n"

	+ " WHEN i.id_user IS NOT NULL THEN u.matricule \r\n"

	+ " ELSE mf.matricule \r\n"

	+ " END AS matricule,\r\n"
	
    + " CASE \r\n"
	
	+ " WHEN i.id_user IS NOT NULL THEN u.adresse \r\n"

	+ " ELSE m.adresse \r\n"

	+ " END AS adresse,\r\n"
	
    + " CASE \r\n"
	
	+ " WHEN i.id_user IS NOT NULL THEN u.telephone \r\n"

	+ " ELSE m.telephone \r\n"

	+ " END AS telephone,\r\n"


	+ " i.date_inscription,\r\n"

	+ " m.id_membre_famille AS id_membre_famille,\r\n"

	+ " d.discipline AS discipline\r\n"

	+ "FROM inscription i\r\n"

	+ "LEFT JOIN utilisateur u ON i.id_user = u.id\r\n"

	+ "LEFT JOIN membre_famille m ON i.id_membre_famille = m.id_membre_famille\r\n"

	+ "LEFT JOIN utilisateur mf ON m.id_user = mf.id\r\n"

	+ "JOIN discipline d ON i.id_discipline = d.id_discipline \r\n"

	+ "WHERE (m.nom LIKE %:recherche% OR m.prenom LIKE %:recherche% OR u.nom LIKE %:recherche% OR u.prenom LIKE %:recherche% OR u.email LIKE %:recherche% OR m.email LIKE %:recherche% OR mf.nom LIKE %:recherche%)and (i.status='accepte') and (discipline =:disModerateur) "

	+ "ORDER BY i.date_inscription DESC ",nativeQuery = true)

	List<Object[]> RechercheMembresParModerateur(@Param(value = "recherche")String recherche , @Param(value= "disModerateur") String discipline ) ;
	
  //  List<Inscription> findByUserId(Long userId);
	  @Query("SELECT i.discipline FROM Inscription i WHERE i.user.id = :userId")
	    List<Discipline> findDisciplinesByUserId(@Param("userId") Long userId);
}
