package com.petshop.petshop.mappper.dto;

import com.petshop.petshop.model.enums.Color;
import com.petshop.petshop.model.PetType;
import lombok.Builder;

@Builder
public record PetDto (Long id, String name, Integer age, String imageurl, Float weight, Color color, PetType pet_type){ }
