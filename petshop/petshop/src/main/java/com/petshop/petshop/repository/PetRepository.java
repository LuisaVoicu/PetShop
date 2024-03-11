package com.petshop.petshop.repository;

import com.petshop.petshop.model.Pet;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends CrudRepository<Pet,Long> {
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"name"})
    Optional<Pet> findByName(String name);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"id"})
    List<Pet> findAll();
}
