package com.petshop.petshop.mappper;

import com.petshop.petshop.mappper.dto.ProductDto;
import com.petshop.petshop.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ProductMapper {

    public ProductDto productEntityToDto(Product product){
        return ProductDto
                .builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .category(product.getCategory())
                .build();
    }

    public List<ProductDto> productListEntitytoDto(List<Product> productList){
        return productList.stream()
                .map(product -> productEntityToDto(product))
                .toList();
    }

    public Product productDtoToEntity(ProductDto productDto){
        return Product
                .builder()
                .id(productDto.id())
                .name(productDto.name())
                .description(productDto.description())
                .category(productDto.category())
                .build();
    }



    public List<Product> productListDtoToEntity(List<ProductDto> productListDto){
        return productListDto.stream()
                .map(productDto -> productDtoToEntity(productDto))
                .toList();
    }
}
