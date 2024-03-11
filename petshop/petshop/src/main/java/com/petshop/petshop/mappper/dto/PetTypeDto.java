package com.petshop.petshop.mappper.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record PetTypeDto (String name, List<PetDto> pets){ }
