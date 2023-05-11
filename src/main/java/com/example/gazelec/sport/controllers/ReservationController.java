package com.example.gazelec.sport.controllers;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.gazelec.sport.models.Inscription;
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
	
	
	/*@GetMapping("/hoiraires")
	public ResponseEntity<List<Reservation>> getReservationsByDate(@RequestParam("date") Date date) {
	    Optional<List<Reservation>> reservations = reservationRepo.findByDate(date);
	    if (reservations.isPresent()) {
	        List<Reservation> reservationsList = reservations.get();
	        return ResponseEntity.ok(reservationsList);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}*/
	/*@GetMapping("/hoiraires")
	public  List<String>  getReservationsByDate(@RequestParam("date") Date date) {
		  Optional<List<Reservation>> reservations = reservationRepo.findByDate(date);
	        if (reservations.isPresent()) {
	            List<Reservation> reservationsList = reservations.get();
	            return reservationsList.stream().map(reservation -> reservation.getHdebut() + " - " + reservation.getHfin()).collect(Collectors.toList());
	        } else {
	            return Collections.emptyList();
	        }
	}*/
	/*@GetMapping("/horaires")
	public List<String[]> getReservationsByDate(@RequestParam("date") Date date) {
	    Optional<List<Reservation>> reservations = reservationRepo.findByDate(date);
	    if (reservations.isPresent()) {
	        List<Reservation> reservationsList = reservations.get();
	        List<String[]> horaires = new ArrayList<>();
	        for (Reservation reservation : reservationsList) {
	            horaires.add(new String[] { reservation.getHdebut(), reservation.getHfin() });
	        }
	        return horaires;
	    } else {
	        return Collections.emptyList();
	    }
	}*/
	// la fonction getReservationByDate version 1  
	
	/*@GetMapping("/horaires/{dateString}")
	public List<String[]> getReservationsByDate(@PathVariable String dateString ) {
		
		
	
		try {
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    Date date = new Date(sdf.parse(dateString).getTime());
		    Optional<List<Reservation>> reservations = reservationRepo.findByDate(date);
		    if (reservations.isPresent()) {
		        List<Reservation> reservationsList = reservations.get();
		        List<String[]> horaires = new ArrayList<>();
		        for (Reservation reservation : reservationsList) {
		            horaires.add(new String[] { reservation.getHdebut(), reservation.getHfin() });
		        }
		        return horaires;
		    } else {
		        return Collections.emptyList();
		    }
		} catch (ParseException e) {
		    // Gérer l'erreur de la conversion de la chaîne de caractères en objet Date
		    return Collections.emptyList();
		}
 
	}*/
	
	@GetMapping("/horaires/{dateString}/{terrainId}")
	public List<String[]> getReservationsByDateAndTerrain(@PathVariable String dateString, @PathVariable Long terrainId) {

	    try {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        Date date = new Date(sdf.parse(dateString).getTime());
	        Optional<List<Reservation>> reservations = reservationRepo.findByDateAndTerrain(date, terrainId);
	        if (reservations.isPresent()) {
	            List<Reservation> reservationsList = reservations.get();
	            List<String[]> horaires = new ArrayList<>();
	            for (Reservation reservation : reservationsList) {
	                horaires.add(new String[] { reservation.getHdebut(), reservation.getHfin() });
	            }
	            return horaires;
	        } else {
	            return Collections.emptyList();
	        }
	    } catch (ParseException e) {
	        // Gérer l'erreur de la conversion de la chaîne de caractères en objet Date
	        return Collections.emptyList();
	    }

	}


	 @GetMapping("/consultation/{userId}")
	    public ResponseEntity<List<Reservation>> getAllReservationsByUserId(@PathVariable("userId") Long userId) {
	        Optional<List<Reservation>> optionalReservations = reservationRepo.findByStatus(userId);
	        if (optionalReservations.isPresent()) {
	            List<Reservation> reservations = optionalReservations.get();
	            return ResponseEntity.ok().body(reservations);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	 @DeleteMapping ("/{id}")
	 public void deleteReservation (@PathVariable Long id ) {
		 reservationRepo.deleteById(id) ; 
		 
		 }
	
	 @GetMapping("/annuler/{resId}")
	    public void annulerMembre(@PathVariable long resId  )  {
		    
		 Optional<Reservation> res = reservationRepo.findById(resId) ;
				
	        	Reservation reservation =res.get();
	        	
	            
	        	reservation.setStatus("annulée");
	        	reservationRepo.save(reservation);      
	    
	    }
	 
	 @GetMapping("/Notifications")
	    public List<Reservation> ReservationsEnAtentte()  {
		    
		      
	    return reservationRepo.findReservationsEnAttente();
	    }
	
}
