package com.example.gazelec.sport.respositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.gazelec.sport.models.User;


public interface UserRepository extends JpaRepository<User, Long> {
	Boolean existsByEmail(String email);
	Optional<User> findByEmail(String email);
	@Query(value = "SELECT * FROM utilisateur WHERE reset_password_token = :token",nativeQuery = true)
	 Optional<User> findByResetToken(@Param(value = "token") String token);
	
	
	@Query(value = "SELECT * FROM utilisateur u INNER JOIN roles r ON u.id_role = r.id_role WHERE r.name = :roleNom",nativeQuery = true)
    public List<User> ListByRole(@Param(value = "roleNom") String role);
	
	@Query(value = "SELECT * FROM utilisateur u INNER JOIN roles r ON u.ID_ROLE = r.ID_ROLE WHERE r.name = :roleNom and (u.nom LIKE %:recherche% OR u.prenom LIKE %:recherche% OR u.email LIKE %:recherche% ) ",nativeQuery = true)


	public List<User> RechercherUtilisateur(@Param(value = "roleNom") String role ,@Param(value = "recherche") String critere);
	@Query(value = "SELECT u.*,  d.discipline AS nom_discipline FROM utilisateur u  INNER JOIN roles r ON u.ID_ROLE = r.ID_ROLE  INNER JOIN discipline d ON u.Id_Discipline = d.Id_Discipline WHERE r.role = :roleNom",nativeQuery = true)
    public List<User> ListeUtilisateursetDisciplines(@Param(value = "roleNom") String role);

}
