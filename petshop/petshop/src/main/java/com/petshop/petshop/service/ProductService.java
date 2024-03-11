package com.petshop.petshop.service;

import com.petshop.petshop.mappper.dto.ProductDto;
import com.petshop.petshop.model.Product;

import java.util.List;

public interface ProductService {
    public ProductDto getProductDtoById(Long id);

    public Product getProductById(Long id);


    public List<ProductDto> getAllProductDtos();

    public List<Product> getAllProducts();

    public ProductDto createProductDto(Product category);

    public ProductDto updateProductDto(Product category);

    public Product updateProduct(Product product);
    public void deleteProduct(Product category);
}
