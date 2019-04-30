package com.example.heroes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.heroes.model.Heroe;


public interface HeroeDao extends JpaRepository<Heroe, Long> {
	
	public List<Heroe> findByNombre(String nombre);

}
