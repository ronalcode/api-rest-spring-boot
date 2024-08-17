package com.localhost.main.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.localhost.main.Entities.Maker;

@Repository
public interface MakerRepository extends CrudRepository<Maker, Long>{

}
