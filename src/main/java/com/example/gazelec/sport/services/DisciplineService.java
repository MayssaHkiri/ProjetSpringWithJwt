package com.example.gazelec.sport.services;

import java.util.List;

import com.example.gazelec.sport.models.Discipline;
import com.example.gazelec.sport.models.Entraîneur;



public interface DisciplineService {
	public List<Discipline> ConsulterDisciplines () ; 
	public Discipline ConsulterDisciplineById (Long id); 
}
