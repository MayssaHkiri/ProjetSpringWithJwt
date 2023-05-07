package com.example.gazelec.sport.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;
import org.apache.commons.io.FileUtils;
import org.apache.catalina.connector.Response;
import org.apache.commons.io.FilenameUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.multipart.MultipartFile;

import com.example.gazelec.sport.exception.ResourceNotFoundException;
import com.example.gazelec.sport.models.Evénement;
import com.example.gazelec.sport.models.User;
import com.example.gazelec.sport.respositories.EvénementRepository;
import com.example.gazelec.sport.services.EvénementService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper; 

@RequestMapping ("/api")
@CrossOrigin("*")
@RestController
public class EvénementController {
	@Autowired
	private EvénementService EvénementService;
	@Autowired 
	private EvénementRepository repository ; 
	@Autowired  ServletContext context;
	@PostMapping("/ajouter")
	public Evénement ajouterEvénement (@RequestBody Evénement E)
	{
		return EvénementService.AjouterEvénement(E);
	}
	@GetMapping("/Liste")
	public List<Evénement> ListeDesEvénements ()
	{
		 return EvénementService.ListeDesEvénements();
	}
	@GetMapping("/Consulter/{id}")
	public Evénement ConsulterEvénement (@PathVariable long id)
	{
		return EvénementService.ConsulterEvénementById(id);
	}
	@DeleteMapping("/Supprimer/{id}")
	public void SupprimerEvénement (@PathVariable long id)
	{
		EvénementService.SupprimerEvénement(id);
	}
	// rechercher un evenement 
	 @GetMapping("/searchEvent/{critere}")
     List<Evénement> SearchEvent( @PathVariable String critere ){
        return repository.RechercherEvent(critere);
   }
	//ajouter un evenement 
	@PostMapping("/events")
	 public ResponseEntity<Response> createEvent  (@RequestParam("file") MultipartFile file,
			 @RequestParam("event") String event) throws JsonParseException , JsonMappingException , Exception
	 {
		 System.out.println("Ok .............");
        Evénement evenement  = new ObjectMapper().readValue(event, Evénement.class);
        boolean isExit = new File(context.getRealPath("/Images/")).exists();
        System.out.println (isExit); 
        if (!isExit)
        {
        	new File (context.getRealPath("/Images/")).mkdir();
        	System.out.println("mk dir.............");
        }
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
        File serverFile = new File (context.getRealPath("/Images/"+File.separator+newFileName));
        try
        {
        	System.out.println("Image");
        	 FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
        	 
        }catch(Exception e) {
        	e.printStackTrace();
        }
        

       
        evenement.setFileName(newFileName);
        Evénement e  = repository.save(evenement);
        if (e != null)
        {
        	return new ResponseEntity<Response>(HttpStatus.OK);
        }
        else
        {
        	return new ResponseEntity<Response>(HttpStatus.BAD_REQUEST);	
        }
	 }
	
	
	//get all events 
	@GetMapping ("/getAll")
	 public ResponseEntity<List<String>> getAll()
	 {
		 List<String> listEvents = new ArrayList<String>();
		 String filesPath = context.getRealPath("/Images");
		 File filefolder = new File(filesPath);
		 if (filefolder != null)
		 {
			for (File file :filefolder.listFiles())
			{
				if(!file.isDirectory())
				{
				  String encodeBase64 = null;
				  try {
					  String extension = FilenameUtils.getExtension(file.getName());
					  FileInputStream fileInputStream = new FileInputStream(file);
				      byte[] bytes = new byte[(int)file.length()];
				      fileInputStream.read(bytes);
				      encodeBase64 = Base64.getEncoder().encodeToString(bytes);
				      listEvents.add("data:image/"+extension+";base64,"+encodeBase64);
				      fileInputStream.close();
				      
				      
				  }catch (Exception e){
					  
				  }
				}
			}
		 }
		 return new ResponseEntity<List<String>>(listEvents,HttpStatus.OK);
	 }
	// get list of events 
	 @GetMapping("/events")
	  public List<Evénement> getAllEvents() {
	     System.out.println("Get all events...");
	 
	    List<Evénement> events = new ArrayList<>();
	    repository.findAll().forEach(events::add);
	 
	    return events;
	  }
	 // get image 
	 @GetMapping(path="/Imgevents/{id}")
	 public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
		 Evénement  event   = repository.findById(id).get();
		 return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+event.getFileName()));
	 }
	// delete event  by id 
	 @DeleteMapping("/events/{id}")
		public Map<String, Boolean> deleteArticle(@PathVariable(value = "id") Long EventId)
				throws ResourceNotFoundException {
			Evénement event = repository.findById(EventId)
					.orElseThrow(() -> new ResourceNotFoundException("Article not found  id :: " + EventId));
			repository.delete(event);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
		}
	 // update event 
	 @PutMapping("modifier")
	 public Evénement updateEvent (@RequestBody Evénement event )
	 {
		 Optional<Evénement> EventInfo = repository.findById(event.getId()); 
		 if (EventInfo.isPresent())  {
			 Evénement ev =   EventInfo.get() ;  
			    ev.setTitre(event.getTitre()) ; 
	        	ev.setDate(event.getDate()); 
	        	ev.setDescription(event.getDescription()); 
	        	ev.setLieu(event.getLieu()); 
	        	ev.setNb_consultation(event.getNb_consultation());
	        	 return repository.save(ev) ; 
		 }
		 return null ; 
	 } 
	 @GetMapping("/events/{id}")
		public ResponseEntity<Evénement> getEventById(@PathVariable(value = "id") Long Id)
				throws ResourceNotFoundException {
			Evénement event = repository.findById(Id)
					.orElseThrow(() -> new ResourceNotFoundException("event not found  " + Id));
			return ResponseEntity.ok().body(event);
		}
	 
	 
	
	 
}
