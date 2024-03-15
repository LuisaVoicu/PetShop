package com.petshop.petshop.service;

import com.petshop.petshop.mappper.ProductMapper;
import com.petshop.petshop.mappper.dto.ProductDto;
import com.petshop.petshop.model.Category;
import com.petshop.petshop.model.Product;
import com.petshop.petshop.repository.CategoryRepository;
import com.petshop.petshop.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDto getProductDtoById(Long id) {
        return productMapper.productEntityToDto(productRepository.findById(id).orElse(null));
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<ProductDto> getAllProductDtos() {
        return productMapper.productListEntitytoDto(productRepository.findAll());
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductDto createProductDto(Product product) {
        return productMapper.productEntityToDto(productRepository.save(product));
    }

    @Override
    public ProductDto updateProductDto(Product product) {
        return productMapper.productEntityToDto(productRepository.save(product));
    }

    @Override
    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }


    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }
}
