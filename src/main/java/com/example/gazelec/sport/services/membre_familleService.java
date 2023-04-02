package com.example.gazelec.sport.services;

import java.util.List;

import com.example.gazelec.sport.models.membre_famille;

public interface membre_familleService {
	public  membre_famille Ajoutermembre (membre_famille m ); 
	public List<membre_famille> Consultermembres () ; 
	public membre_famille ConsulterMembreById (Long id); 
	public void SupprimeMembreById (Long id); 
	
}
