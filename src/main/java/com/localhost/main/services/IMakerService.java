package com.localhost.main.services;

import java.util.List;
import java.util.Optional;

import com.localhost.main.Entities.Maker;

public interface IMakerService {
	List<Maker> findAll();
	
	Optional<Maker> findById(Long id);
	
	void save(Maker maker);
	
	void deleteById(Long id);
}
