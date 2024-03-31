package com.petshop.petshop.mappper.dto;

import com.petshop.petshop.model.Color;
import com.petshop.petshop.model.PetType;
import lombok.Builder;

@Builder
public record PetDto (Long id, String name, Integer age, Float weight, Color color, PetType petType){ }
