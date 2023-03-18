package com.example.gazelec.sport.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gazelec.sport.models.Entraîneur;

public interface EntraîneurRepository extends  JpaRepository <Entraîneur , Long > {

}
