package com.petshop.petshop.service.impl;

import com.petshop.petshop.mappper.PetMapper;
import com.petshop.petshop.mappper.dto.PetDto;
import com.petshop.petshop.model.Pet;
import com.petshop.petshop.repository.PetRepository;
import com.petshop.petshop.service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;
    private final PetMapper petMapper;

    @Override
    public PetDto getPetDtoById(Long id){
        return petMapper.petEntityToDto(
                petRepository.findById(id).orElse(null)
        );
    }

    @Override
    public Pet getPetById(Long id){
        return petRepository.findById(id).orElse(null);
    }

    @Override
    public Pet getPetByName(String name) {
        return petRepository.findByName(name).orElse(null);
    }

    @Override
    public List<PetDto> getAllPetDtos() {
        return petMapper.petListEntityToDto(petRepository.findAll());
    }

    @Override
    public PetDto createPetDtoFromEntity(Pet pet) {
        return petMapper.petEntityToDto(petRepository.save(pet));
    }

    @Override
    public PetDto updatePetDtoFromEntity(Pet pet) {
        return petMapper.petEntityToDto(petRepository.save(pet));
    }

    @Override
    public void deletePet(Pet pet) {
        if(pet != null){
            System.out.println("Deleted:" + pet.getName());
            petRepository.delete(pet);
        }else{
            System.out.println("Can't delete : " + pet.getName());
        }
    }
}
