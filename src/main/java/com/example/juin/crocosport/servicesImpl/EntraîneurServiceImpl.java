package com.example.juin.crocosport.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.juin.crocosport.models.Entraîneur;
import com.example.juin.crocosport.respositories.EntraîneurRepository;
import com.example.juin.crocosport.services.EntraîneurService;


@Service 
public class EntraîneurServiceImpl  implements EntraîneurService {
     
	@Autowired
	private EntraîneurRepository EntraîneurRepo ; 
	@Override
	public Entraîneur AjouterEntraîneur(Entraîneur e) {
		return EntraîneurRepo.save(e);
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
	public Entraîneur ModifierEntraîneur(Entraîneur En) {
		return EntraîneurRepo.save(En);
	}
    
	

}
