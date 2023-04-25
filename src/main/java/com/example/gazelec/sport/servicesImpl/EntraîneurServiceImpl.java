package com.example.gazelec.sport.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.gazelec.sport.models.Discipline;
import com.example.gazelec.sport.models.Entraîneur;
import com.example.gazelec.sport.models.Evénement;
import com.example.gazelec.sport.models.User;
import com.example.gazelec.sport.respositories.DisciplineRepository;
import com.example.gazelec.sport.respositories.EntraîneurRepository;
import com.example.gazelec.sport.services.EntraîneurService;


@Service 
public class EntraîneurServiceImpl  implements EntraîneurService {
     
	@Autowired
	private EntraîneurRepository EntraîneurRepo ; 
	@Autowired 
	private DisciplineRepository DisciplineRepo ; 
	
	@Override
	public Entraîneur AjouterEntraîneur(Entraîneur e , Long id ) {
		
		Entraîneur en = new Entraîneur  (e.getNom() , e.getPrenom(), e.getEmail() , e.getAdresse(),  e.getTelephone() , e.getNaissance());
		Optional <Discipline> disciplineInfo =  DisciplineRepo.findById(id) ; 
		if (disciplineInfo.isPresent()) 
		{
			Discipline d = disciplineInfo.get() ; 
			en.setDiscipline(d); 
		}
		
		return EntraîneurRepo.save(en);
	}

	@Override
	public List<Entraîneur> ConsulterEntraîneurs() {
		return EntraîneurRepo.findAll();
	}

	@Override
	public Entraîneur ConsulterEntraîneurById(Long id) {
		Optional<Entraîneur> m =EntraîneurRepo.findById(id) ;
		 return m.isPresent() ? m.get() : null ; 
	}

	@Override
	public void SupprimeEntraîneurById(Long id) {
		
		EntraîneurRepo.deleteById(id);
	}

	@Override
	public Entraîneur ModifierEntraîneur(Entraîneur En ) {
		Optional <Entraîneur> entraineurInfo = EntraîneurRepo.findById(En.getId()) ; 
		if (entraineurInfo.isPresent()) {
			Entraîneur e = entraineurInfo.get() ; 
			e.setNom(En.getNom());
			e.setPrenom(En.getPrenom()) ; 
			e.setEmail(En.getEmail());
			e.setTelephone(En.getTelephone());
			e.setNaissance(En.getNaissance());
			e.setAdresse(En.getAdresse());
		/*Optional <Discipline> disciplineInfo = DisciplineRepo.findById(id) ; 
		   if (disciplineInfo.isPresent()) {
			   Discipline d = disciplineInfo.get() ; 
			   e.setDiscipline(d);
		   }*/
		   
		  return  EntraîneurRepo.save(e) ; 
		   
		}
		else {
			return null ; 
		}
	}

	@Override
	public List<Entraîneur> ListeEntraineurs() {
		
		return EntraîneurRepo.ListeEntraineursetDisciplines() ;
	}

	@Override
	public List<Entraîneur> RechercherEntraineurs(String recherche) {
		return EntraîneurRepo.RechercherEntraineurs(recherche) ; 
	}
    
	
	

}
