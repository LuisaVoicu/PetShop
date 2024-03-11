package com.petshop.petshop.service;

import com.petshop.petshop.mappper.dto.PetTypeDto;
import com.petshop.petshop.model.PetType;

import java.util.List;

public interface PetTypeService {
    public PetTypeDto getPetTypeDtoById(Long id);

    public PetTypeDto getPetTypeDtoByPetType(String petType);

    public List<PetTypeDto> getAllPetTypeDtos();

    public List<PetType> findAllPetTypes();

    public PetTypeDto createPetTypeDto(PetType petType);

    public PetTypeDto updatePetTypeDto(PetType petType);

    public void deletePetType(PetType petType);

}
