package com.petshop.petshop.controller;


import com.petshop.petshop.mappper.dto.ProductDto;
import com.petshop.petshop.model.Product;
import com.petshop.petshop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    // CREATE
    @GetMapping("/products/create")
    public String displayCreateProductForm(Model model){
        model.addAttribute("product", new Product());
        return "/product/create";
    }

    @PostMapping({"/products/create"})
    public String processCreateProductForm(@ModelAttribute("product") Product product, RedirectAttributes redirectAttributes){

        ProductDto productDto = productService.createProductDto(product);
        redirectAttributes.addAttribute("productCreation", "Success");
        return "redirect:/products";
    }


    // RETRIEVE

    @GetMapping("/products")
    public String getProducts (Model model){
        List<ProductDto> productDtos = productService.getAllProductDtos();
        model.addAttribute("title", "Products");
        model.addAttribute("products", productDtos);

        return "products";
    }

    @GetMapping("/products/{id}")
    public ProductDto getProductsById(@PathVariable Long id){
        return productService.getProductDtoById(id);
    }


    // UPDATE

    @GetMapping({"/products/update"})
    public String displayEditUserForm(Model model) {
        model.addAttribute("title", "Edit products");
        model.addAttribute("products", this.productService.getAllProductDtos());
        return "product/update";
    }


    @GetMapping({"products/update-details"})
    public String displayProductEditDetails(@RequestParam Long id, Model model) {

        ProductDto productDto = this.productService.getProductDtoById(id);

        model.addAttribute("name", productDto.name() + " Details");
        model.addAttribute("product", productDto);

        return "product/update-details";
    }

    @PostMapping({"products/update-details"})
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

    @GetMapping("/products/delete")
    public String displayDeleteProductsForm(Model model){
        model.addAttribute("title", "Delete Products");
        model.addAttribute("products", this.productService.getAllProducts());
        return "product/delete";
    }

    @PostMapping("/products/delete")
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


}
