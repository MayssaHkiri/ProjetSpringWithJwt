package com.example.gazelec.sport.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gazelec.sport.models.Discipline;
import com.example.gazelec.sport.models.Entraîneur;
import com.example.gazelec.sport.respositories.DisciplineRepository;
import com.example.gazelec.sport.services.DisciplineService;

@Service 
public class DisciplineServiceImpl implements DisciplineService  {
    
	
	@Autowired 
	private DisciplineRepository disciplineRepo ; 
	@Override
	public List<Discipline> ConsulterDisciplines() {
		
		return disciplineRepo.findAll();
	}
	@Override
	public Discipline ConsulterDisciplineById(Long id) {
		Optional<Discipline> m =disciplineRepo.findById(id) ;
		 return m.isPresent() ? m.get() : null ; 
	}

}
