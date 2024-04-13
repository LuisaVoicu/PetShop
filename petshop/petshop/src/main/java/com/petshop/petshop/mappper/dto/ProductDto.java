package com.petshop.petshop.mappper.dto;

import com.petshop.petshop.model.Category;
import lombok.Builder;
import java.util.List;

//todo add Category category,  field
@Builder
public record ProductDto (Long id, String name, String description,String imageurl, List<String> tags, Boolean favourite, Integer stars, Double price){

}
