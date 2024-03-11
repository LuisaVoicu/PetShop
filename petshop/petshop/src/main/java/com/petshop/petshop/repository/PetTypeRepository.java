package com.petshop.petshop.repository;

import com.petshop.petshop.model.PetType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetTypeRepository extends CrudRepository<PetType, Long> {
    PetType findByName(String name);
    List<PetType> findAll();
}
