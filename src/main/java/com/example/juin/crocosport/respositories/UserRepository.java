package com.example.juin.crocosport.respositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.example.juin.crocosport.models.User;


public interface UserRepository extends JpaRepository<User, Long> {
	Boolean existsByEmail(String email);
	Optional<User> findByEmail(String email);
	
	
	@Query(value = "SELECT * FROM users u INNER JOIN roles r ON u.ID_ROLE = r.ID_ROLE WHERE r.name = :roleNom",nativeQuery = true)
    public List<User> ListByRole(@Param(value = "roleNom") String role);
	
	@Query(value = "SELECT * FROM users u INNER JOIN roles r ON u.ID_ROLE = r.ID_ROLE WHERE r.name = :roleNom and (u.nom LIKE %:recherche% OR u.prenom LIKE %:recherche% OR u.email LIKE %:recherche% ) ",nativeQuery = true)


	public List<User> RechercherUtilisateur(@Param(value = "roleNom") String role ,@Param(value = "recherche") String critere);

}
