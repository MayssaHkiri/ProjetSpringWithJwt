package com.example.gazelec.sport.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gazelec.sport.models.Entraîneur;
import com.example.gazelec.sport.models.User;
import com.example.gazelec.sport.models.membre_famille;
import com.example.gazelec.sport.respositories.UserRepository;
import com.example.gazelec.sport.respositories.membre_familleRepository;
import com.example.gazelec.sport.services.UserService;
import com.example.gazelec.sport.services.membre_familleService;

@Service 
public class membre_familleServiceImpl implements membre_familleService {
	
	@Autowired
	private membre_familleRepository MembreRepo ;
	
	

	@Override
	public membre_famille Ajoutermembre(membre_famille m ) {
		
		 return MembreRepo.save(m);
	}

	@Override
	public List<membre_famille> Consultermembres() {
		
		return MembreRepo.findAll();
	}

	@Override
	public membre_famille ConsulterMembreById(Long id) {
		Optional<membre_famille> m =MembreRepo.findById(id) ;
		 return m.isPresent() ? m.get() : null ; 
	}

	@Override
	public void SupprimeMembreById(Long id) {
		MembreRepo.deleteById(id);
		
	}


	
}
