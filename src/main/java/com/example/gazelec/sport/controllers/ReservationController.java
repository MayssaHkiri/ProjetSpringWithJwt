package com.example.gazelec.sport.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.gazelec.sport.models.MessageResponse;
import com.example.gazelec.sport.models.Reservation;
import com.example.gazelec.sport.models.Terrain;
import com.example.gazelec.sport.models.User;
import com.example.gazelec.sport.respositories.ReservationRepository;
import com.example.gazelec.sport.respositories.TerrainRepository;
import com.example.gazelec.sport.respositories.UserRepository;

@RequestMapping ("/reservation")
@CrossOrigin("*")
@RestController
public class ReservationController {

	@Autowired
	private UserRepository utilRepo ;
	@Autowired 
	private TerrainRepository terrainRepo ;
	@Autowired 
	private ReservationRepository reservationRepo ; 
	
	@PostMapping("/ajouter")
	public ResponseEntity<?> ajouterReservation (@RequestBody Reservation R , @RequestParam Long idUser , @RequestParam Long idTerrain)
	
	{
		 Optional<User> optionalUser = utilRepo.findById(idUser);
		 if (optionalUser.isPresent()) { 
		        User existingUser = optionalUser.get();
		        R.setUser(existingUser);
		        System.out.println("voula l'id user"+existingUser.getNom());
		        }
	else {
		 return ResponseEntity.badRequest().body(new MessageResponse("Error : utilisateur n'existe pas ! "));
	}
		 
		 Optional<Terrain> optionalTerrain = terrainRepo.findById(idTerrain); 
		 if (optionalTerrain.isPresent()) {
			 Terrain existingTerrain = optionalTerrain.get(); 
			 R.setTerrain(existingTerrain); 
		 }
		 else {
			 return ResponseEntity.badRequest().body(new MessageResponse("Error : terrain n'existe pas ! ")); 
		 }
		 reservationRepo.save(R); 
		   return ResponseEntity.ok(new MessageResponse("Reservation registered successfully!"));
		 
}
}
