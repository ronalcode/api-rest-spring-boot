package com.localhost.main.services.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.localhost.main.Entities.Maker;
import com.localhost.main.persintence.IMakerDAO;
import com.localhost.main.services.IMakerService;

@Service
public class MakerServiceImpl implements IMakerService {
	@Autowired
	private IMakerDAO makerDAO;
	
	@Override
	public List<Maker> findAll() {
		return makerDAO.findAll();
	}

	@Override
	public Optional<Maker> findById(Long id) {
		return makerDAO.findById(id);
	}

	@Override
	public void save(Maker maker) {
		makerDAO.save(maker);
	}

	@Override
	public void deleteById(Long id) {
		makerDAO.deleteById(id);
	}

}
