package com.petshop.petshop.mappper.dto;

import com.petshop.petshop.model.Category;
import lombok.Builder;

@Builder
public record ProductDto (Long id, String name, String description, Category category, String imageURL, Double price){
}
