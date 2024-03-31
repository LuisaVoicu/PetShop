package com.petshop.petshop.mappper;

import com.petshop.petshop.mappper.dto.PetDto;
import com.petshop.petshop.model.Pet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class PetMapper {
    public PetDto petEntityToDto(Pet pet){
        return PetDto.builder()
                .id(pet.getId())
                .name(pet.getName())
                .weight(pet.getWeight())
                .age(pet.getAge())
                .petType(pet.getPetType())
                .build();
    }

    public List<PetDto> petListEntityToDto(List<Pet> pets){
        return pets.stream()
                .map(pet-> petEntityToDto(pet))
                .toList();
    }

    public Pet petDtoToEntity(PetDto petDto){
        return Pet.builder()
                .id(petDto.id())
                .name(petDto.name())
                .age(petDto.age())
                .weight(petDto.weight())
                .petType(petDto.petType())
                .build();
    }

    public List<Pet> petListDtoToEntity(List<PetDto> petDtos){
        return petDtos.stream()
                .map(petDto -> petDtoToEntity(petDto))
                .toList();
    }
}
