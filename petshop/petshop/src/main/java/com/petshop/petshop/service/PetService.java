package com.petshop.petshop.service;

import com.petshop.petshop.mappper.dto.PetDto;
import com.petshop.petshop.model.Pet;

import java.util.List;

public interface PetService {

    PetDto getPetDtoById(Long id);

    Pet getPetById(Long id);

    Pet getPetByName(String name);

    List<PetDto> getAllPetDtos();

    PetDto createPetDtoFromEntity(Pet pet);

    PetDto updatePetDtoFromEntity(Pet pet);

    void deletePet(Pet pet);
}
