package com.petshop.petshop.controller;


import com.petshop.petshop.mappper.dto.CredentialsDto;
import com.petshop.petshop.mappper.dto.ProductDto;
import com.petshop.petshop.mappper.dto.SignUpDto;
import com.petshop.petshop.mappper.dto.UserDto;
import com.petshop.petshop.model.Product;
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

    // CREATE
    @GetMapping("/product/create")
    public String displayCreateProductForm(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("categories", productService.findAllCategory());
        return "/product/create";
    }

    @PostMapping({"/product/create"})
    public String processCreateProductForm(@ModelAttribute("product") Product product, RedirectAttributes redirectAttributes){

        ProductDto productDto = productService.createProductDto(product);
        redirectAttributes.addAttribute("productCreation", "Success");
        return "redirect:/products";
    }


    // RETRIEVE

/*    @GetMapping("/product")
    public String getProducts (Model model){
        List<ProductDto> productDtos = productService.getAllProductDtos();
        model.addAttribute("title", "Products");
        model.addAttribute("products", productDtos);

        return "products";
    }*/

/*    @GetMapping("/product")
    public List<ProductDto> getProducts() {
        return productService.getAllProductDtos();
    }*/
/*
    @GetMapping("/product")
    public ResponseEntity<ProductDto> getProducts() {
        ProductDto productDto = productService.getAllProductDtos().get(0);
        return ResponseEntity.ok(productDto);
    }
*/


    @GetMapping("/product/{id}")
    public ProductDto getProductsById(@PathVariable Long id){
        return productService.getProductDtoById(id);
    }


    // UPDATE

    @GetMapping({"/product/update"})
    public String displayEditUserForm(Model model) {
        model.addAttribute("title", "Edit products");
        model.addAttribute("products", this.productService.getAllProductDtos());
        return "product/update";
    }


    @GetMapping({"product/update-details"})
    public String displayProductEditDetails(@RequestParam Long id, Model model) {

        ProductDto productDto = this.productService.getProductDtoById(id);

        model.addAttribute("name", productDto.name() + " Details");
        model.addAttribute("product", productDto);
        model.addAttribute("categories", productService.findAllCategory());

        return "product/update-details";
    }

    @PostMapping({"product/update-details"})
    public String processEditProductForm(@ModelAttribute("product") Product editedProduct, Errors errors, Model model) {


        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit Product");
            return "product/update-details";
        } else {

            Product product = productService.getProductById(editedProduct.getId());

            if(product != null){
                product.setCategory(editedProduct.getCategory());
                product.setDescription(editedProduct.getDescription());
                product.setName(editedProduct.getName());
                productService.updateProduct(product);
            }
            return "redirect:/products";
        }

    }




    // DELETE

/*
    @GetMapping("/product/delete")
    public String displayDeleteProductsForm(Model model){
        model.addAttribute("title", "Delete Products");
        model.addAttribute("products", this.productService.getAllProducts());
        return "product/delete";
    }

    @PostMapping("/product/delete")
    public String processDeleteProductsForm(@ModelAttribute("id") Long[] productIds){
        if(productIds != null){
            System.out.println("'not null list of products': ");

            for(Long id : productIds){
                Product product = productService.getProductById(id);
                System.out.println("AAAA: " + product.getName());
                if(product != null){
                    System.out.println("'not null product': " + product.getName());

                    productService.deleteProduct(product);
                }
            }
        }
        return "redirect:/products";
    }
*/


    // ENDPOINTS


    @GetMapping("/product")

    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> productDtos = productService.getAllProductDtos();
        return ResponseEntity.ok(productDtos);
    }


    @PostMapping("/product-create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid ProductDto product) {
        ProductDto createdProduct = productService.createProduct(product);
        return ResponseEntity.created(URI.create("/product-create" + createdProduct.id())).body(createdProduct);
    }

/*    @PostMapping("/product-delete")
    public ResponseEntity<ProductDto> deleteProduct(@RequestBody @Valid ProductDto product) {

        Product deletedProduct = productService.getProductById(product.id());

        if(deletedProduct != null) {
            productService.deleteProduct(deletedProduct);
        }

        return ResponseEntity.created(URI.create("/product-delete" + product.id())).body(product);
    }*/


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
        return ResponseEntity.created(URI.create("/product-edit" + editedProduct.id())).body(editedProduct);
    }

/*    @PostMapping("/product-edit-details")
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid ProductDto product) {
        ProductDto createdProduct = productService.createProduct(product);
        return ResponseEntity.created(URI.create("/product-create" + createdProduct.id())).body(createdProduct);
    }*/


}
