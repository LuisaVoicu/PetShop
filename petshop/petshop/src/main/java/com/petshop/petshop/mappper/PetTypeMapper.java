package com.petshop.petshop.mappper;

import com.petshop.petshop.mappper.dto.PetTypeDto;
import com.petshop.petshop.model.PetType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class PetTypeMapper {
    private final PetMapper petMapper;

    public PetTypeDto petTypeEntityToDto(PetType petType) {
        return PetTypeDto
                .builder()
                .name(petType.getName())
                .pets(petMapper.petListEntityToDto(petType.getPets()))
                .build();
    }

    public List<PetTypeDto> petTypeListEntityToDto(List<PetType> petTypes){
        return petTypes.stream()
                .map(petType -> petTypeEntityToDto(petType))
                .toList();
    }

    public PetType petTypeDtoToEntity(PetTypeDto petTypeDto){
        return PetType
                .builder()
                .name(petTypeDto.name())
                .pets(petMapper.petListDtoToEntity(petTypeDto.pets()))
                .build();
    }

    public List<PetType> petTypeListDtoToEntity(List<PetTypeDto> petTypeDtos){
        return petTypeDtos.stream()
                .map(petTypeDto -> petTypeDtoToEntity(petTypeDto))
                .toList();
    }
}
