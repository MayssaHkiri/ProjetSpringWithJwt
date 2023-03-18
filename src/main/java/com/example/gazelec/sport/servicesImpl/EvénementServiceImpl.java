package com.example.gazelec.sport.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gazelec.sport.models.Evénement;
import com.example.gazelec.sport.respositories.EvénementRepository;
import com.example.gazelec.sport.services.EvénementService;

@Service 
public class EvénementServiceImpl implements EvénementService {
     
	@Autowired 
	private EvénementRepository EvénementRepo;
	@Override
	public Evénement AjouterEvénement(Evénement E) {
		return EvénementRepo.save(E) ;
	}

	@Override
	public List<Evénement> ListeDesEvénements() {
		return EvénementRepo.findAll();
	}

	@Override
	public Evénement ConsulterEvénementById(Long id) {
		 Optional<Evénement> m = EvénementRepo.findById(id) ;
		 return m.isPresent() ? m.get() : null ; 
	}

	@Override
	public void SupprimerEvénement(Long id) {
		EvénementRepo.deleteById(id);		
		
	}

}
