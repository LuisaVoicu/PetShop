package com.petshop.petshop.controller;

import com.petshop.petshop.mappper.dto.CategoryDto;
import com.petshop.petshop.model.Category;
import com.petshop.petshop.model.Product;
import com.petshop.petshop.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // CREATE
    @GetMapping("/categories/create")
    public String displayCreateCategoryForm(Model model){
        model.addAttribute("category", new Category());
        return "/category/create";
    }

    @PostMapping({"/categories/create"})
    public String processCreateCategoryForm(@ModelAttribute("category") Category category, RedirectAttributes redirectAttributes){

        List<Product> products = new ArrayList<>();
        category.setProducts(products);

        CategoryDto categoryDto = categoryService.createCategoryDto(category);
        redirectAttributes.addAttribute("categoryCreation", "Success");
        return "redirect:/categories";
    }


    // RETRIEVE

    @GetMapping("/categories")
    public String getCategories (Model model){
        List<CategoryDto> categoryDtos = categoryService.getAllCategoryDtos();
        model.addAttribute("title", "Categories");
        model.addAttribute("categories", categoryDtos);
        return "categories";
    }

    @GetMapping("/categories/{id}")
    public CategoryDto getCategoriesById(@PathVariable Long id){
        return categoryService.getCategoryDtoById(id);
    }


    // UPDATE

    @GetMapping({"/categories/update"})
    public String displayEditUserForm(Model model) {
        model.addAttribute("title", "Edit categories");
        model.addAttribute("categories", this.categoryService.getAllCategoryDtos());
        return "category/update";
    }


    @GetMapping({"categories/update-details"})
    public String displayCategoryEditDetails(@RequestParam Long id, Model model) {

        CategoryDto categoryDto = this.categoryService.getCategoryDtoById(id);

        model.addAttribute("name", categoryDto.name() + " Details");
        model.addAttribute("category", categoryDto);

        return "category/update-details";
    }

    @PostMapping({"categories/update-details"})
    public String processEditCategoryForm(@ModelAttribute("category") Category editedCategory, Errors errors, Model model) {


        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit Category");
            return "category/update-details";
        } else {

            Category category = categoryService.getCategoryById(editedCategory.getId());

            if(category != null){
                category.setPetShop(editedCategory.getPetShop());
                category.setName(editedCategory.getName());
                categoryService.updateCategory(category);
            }
            return "redirect:/categories";
        }

    }




    // DELETE

    @GetMapping("/categories/delete")
    public String displayDeleteCategoriesForm(Model model){
        model.addAttribute("title", "Delete Categories");
        model.addAttribute("categories", this.categoryService.getAllCategories());
        return "category/delete";
    }

    @PostMapping("/categories/delete")
    public String processDeleteCategoriesForm(@ModelAttribute("id") Long[] categoryIds){
        if(categoryIds != null){
            System.out.println("'not null list of categories': ");

            for(Long id : categoryIds){
                Category category = categoryService.getCategoryById(id);
                System.out.println("AAAA: " + category.getName());
                if(category != null){
                    System.out.println("'not null category': " + category.getName());

                    categoryService.deleteCategory(category);
                }
            }
        }
        return "redirect:/categories";
    }


}
