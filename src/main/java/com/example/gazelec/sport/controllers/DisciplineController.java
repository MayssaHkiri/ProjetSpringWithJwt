package com.example.gazelec.sport.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gazelec.sport.models.Discipline;
import com.example.gazelec.sport.services.DisciplineService;

@RequestMapping ("/disciplines")
@CrossOrigin("*")
@RestController
public class DisciplineController {
	@Autowired
	private DisciplineService disciplineService ; 
	@GetMapping("/Consulter")
	public List <Discipline> ListerDisciplines ()
	{
		return disciplineService.ConsulterDisciplines(); 
	}
}
