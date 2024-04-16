package com.petshop.petshop.controller;


import com.petshop.petshop.mappper.dto.CredentialsDto;
import com.petshop.petshop.mappper.dto.ProductDto;
import com.petshop.petshop.mappper.dto.SignUpDto;
import com.petshop.petshop.mappper.dto.UserDto;
import com.petshop.petshop.model.Product;
import com.petshop.petshop.model.User;
import com.petshop.petshop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.awt.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product")

    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> productDtos = productService.getAllProductDtos();
        return ResponseEntity.ok(productDtos);
    }


    @PostMapping("/product-create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody(required = false) @Valid ProductDto product) {

        System.out.println("!!!!---> " + product.imageurl());

        ProductDto createdProduct = productService.createProduct(product);
        return ResponseEntity.created(URI.create("/product-create" + createdProduct.id())).body(createdProduct);
    }

    @PostMapping("/product-delete")
    public ResponseEntity<ProductDto> deleteProduct(@RequestBody(required = false) @Valid ProductDto productDto) {
        if (productDto == null || productDto.id() == null) {
            // Handle case when product or product ID is missing
            return ResponseEntity.badRequest().build();
        }

        Product deletedProduct = productService.getProductById(productDto.id());

        if (deletedProduct != null) {
            productService.deleteProduct(deletedProduct);
            return ResponseEntity.ok().body(productDto); // Product successfully deleted
        } else {
            return ResponseEntity.notFound().build(); // Product not found
        }
    }


    @PostMapping("/product-edit")
    public ResponseEntity<ProductDto> editProduct(@RequestBody(required = false) @Valid ProductDto productDto) {
        if (productDto == null || productDto.id() == null) {
            // Handle case when product or product ID is missing
            return ResponseEntity.badRequest().build();
        }
        System.out.println("AAAAAAAA!!!!" + productDto.id()+ " " + productDto.name() + "--");
        ProductDto editedProduct = productService.updateProductDto(productDto);
        return ResponseEntity.created(URI.create("/product-create" + editedProduct.id())).body(editedProduct);
    }



/*    @PostMapping("/product-edit-details")
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid ProductDto product) {
        ProductDto createdProduct = productService.createProduct(product);
        return ResponseEntity.created(URI.create("/product-create" + createdProduct.id())).body(createdProduct);
    }*/


}
