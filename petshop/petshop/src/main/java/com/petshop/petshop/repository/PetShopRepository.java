package com.petshop.petshop.repository;

import com.petshop.petshop.model.PetShop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetShopRepository extends CrudRepository<PetShop, Long> {
}
