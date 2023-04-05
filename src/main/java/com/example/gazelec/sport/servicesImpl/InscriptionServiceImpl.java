package com.example.gazelec.sport.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gazelec.sport.models.Inscription;
import com.example.gazelec.sport.models.membre_famille;
import com.example.gazelec.sport.respositories.InscriptionRepository;
import com.example.gazelec.sport.respositories.membre_familleRepository;
import com.example.gazelec.sport.services.InscriptionService;

@Service 
public class InscriptionServiceImpl implements InscriptionService {

	@Autowired
	private InscriptionRepository InscriRepo ;
	@Override
	public Inscription AjouterInscription(Inscription i) {
		
		return InscriRepo.save(i);
	}

	@Override
	public List<Inscription> ConsulterInscriptions() {
		
		return InscriRepo.findAll();
	}

	@Override
	public Inscription ConsulterInscriptionById(Long id) {
		Optional<Inscription> m =InscriRepo.findById(id) ;
		 return m.isPresent() ? m.get() : null ; 
	}

	@Override
	public void SupprimeInscriptionById(Long id) {
		InscriRepo.deleteById(id);
		
	}

}
