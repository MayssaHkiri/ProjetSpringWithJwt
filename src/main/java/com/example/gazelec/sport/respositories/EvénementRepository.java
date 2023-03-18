package com.example.gazelec.sport.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gazelec.sport.models.Evénement;



public interface EvénementRepository  extends JpaRepository < Evénement  , Long>   {

}
