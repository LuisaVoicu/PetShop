package com.petshop.petshop.service.impl;

import com.petshop.petshop.mappper.PetTypeMapper;
import com.petshop.petshop.mappper.dto.PetTypeDto;
import com.petshop.petshop.model.PetType;
import com.petshop.petshop.repository.PetTypeRepository;
import com.petshop.petshop.service.PetTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PetTypeServiceImpl implements PetTypeService {

    private final PetTypeRepository petTypeRepository;

    private final PetTypeMapper petTypeMapper;

    @Override
    public PetTypeDto getPetTypeDtoById(Long id){
        return petTypeMapper.petTypeEntityToDto(petTypeRepository.findById(id).orElse(null));
    }

    @Override
    public PetTypeDto getPetTypeDtoByPetType(String petType){
        return petTypeMapper.petTypeEntityToDto(petTypeRepository.findByName(petType));
    }

    @Override
    public List<PetTypeDto> getAllPetTypeDtos(){
        return petTypeMapper.petTypeListEntityToDto(petTypeRepository.findAll());
    }

    @Override
    public List<PetType> findAllPetTypes(){
        return petTypeRepository.findAll();
    }

    @Override
    public PetTypeDto createPetTypeDto(PetType petType){
        return petTypeMapper.petTypeEntityToDto(petTypeRepository.save(petType));
    }

    @Override
    public PetTypeDto updatePetTypeDto(PetType petType){
        return petTypeMapper.petTypeEntityToDto(petTypeRepository.save(petType));
    }

    @Override
    public void deletePetType(PetType petType){
        petTypeRepository.delete(petType);
    }

}