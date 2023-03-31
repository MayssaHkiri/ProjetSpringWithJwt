package com.example.gazelec.sport.controllers;

import java.io.File;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.gazelec.sport.models.Evénement;
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
	
}
