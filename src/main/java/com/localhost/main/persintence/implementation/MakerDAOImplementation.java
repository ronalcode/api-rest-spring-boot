package com.localhost.main.persintence.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.localhost.main.Entities.Maker;
import com.localhost.main.persintence.IMakerDAO;
import com.localhost.main.repositories.MakerRepository;

@Component
public class MakerDAOImplementation implements IMakerDAO{
	@Autowired
	private MakerRepository makerRepository;
	
	@Override
	public List<Maker> findAll() {
		return (List<Maker>) makerRepository.findAll();
	}

	@Override
	public Optional<Maker> findById(Long id) {
		return makerRepository.findById(id);
	}

	@Override
	public void save(Maker maker) {
		makerRepository.save(maker);
	}

	@Override
	public void deleteById(Long id) {
		makerRepository.deleteById(id);
	}

}
