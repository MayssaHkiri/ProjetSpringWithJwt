package com.example.gazelec.sport.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gazelec.sport.models.Discipline;



public interface DisciplineRepository extends  JpaRepository <Discipline , Long >{

}
