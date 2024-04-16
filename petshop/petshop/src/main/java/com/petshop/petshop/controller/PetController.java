package com.petshop.petshop.controller;

import com.petshop.petshop.controller.validation.GlobalExceptionHandlerController;
import com.petshop.petshop.mappper.dto.PetDto;
import com.petshop.petshop.mappper.dto.ProductDto;
import com.petshop.petshop.model.Pet;
import com.petshop.petshop.model.PetType;
import com.petshop.petshop.model.Product;
import com.petshop.petshop.service.PetService;
import com.petshop.petshop.service.PetTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PetController extends GlobalExceptionHandlerController {

    private final PetService petService;

    @GetMapping("/pet")
    public ResponseEntity<List<PetDto>> getAllProducts() {
        List<PetDto> petDtos = petService.getAllPetDtos();

        System.out.println("pets...: ");

        for(PetDto p : petDtos){
            System.out.println(p.name());
        }
        return ResponseEntity.ok(petDtos);
    }


    @PostMapping("/pet-create")
    public ResponseEntity<PetDto> createProduct(@RequestBody @Valid PetDto pet) {
        PetDto createdPet = petService.createPet(pet);
        return ResponseEntity.created(URI.create("pet-create" + createdPet.id())).body(createdPet);
    }


    @PostMapping("/pet-delete")
    public ResponseEntity<PetDto> deleteProduct(@RequestBody(required = false) @Valid PetDto petDto) {
        if (petDto == null || petDto.id() == null) {
            // Handle case when product or product ID is missing
            return ResponseEntity.badRequest().build();
        }

        Pet deletedPet = petService.getPetById(petDto.id());

        if (deletedPet != null) {
            petService.deletePet(deletedPet);
            return ResponseEntity.ok().body(petDto); // Product successfully deleted
        } else {
            return ResponseEntity.notFound().build(); // Product not found
        }
    }


    @PostMapping("/pet-edit")
    public ResponseEntity<PetDto> editProduct(@RequestBody(required = false) @Valid PetDto petDto) {
        if (petDto == null || petDto.id() == null) {
            // Handle case when product or product ID is missing
            return ResponseEntity.badRequest().build();
        }
        PetDto editedPet = petService.updatePetDto(petDto);
        return ResponseEntity.created(URI.create("/pet-edit" + editedPet.id())).body(editedPet);
    }
}
