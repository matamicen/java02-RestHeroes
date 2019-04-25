package com.example.heroes.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.heroes.model.Heroe;

public interface HeroeDao extends JpaRepository<Heroe, Long> {

}
