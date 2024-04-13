package com.petshop.petshop.controller;

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
public class PetController {

    private final PetService petService;
    private final PetTypeService petTypeService;



    // CREATE
    @GetMapping({"/pet/create"})
    public String displayCreatePetForm(Model model) {

        //  model.addAttribute("title", "Register");
        model.addAttribute("pet", new Pet());
        model.addAttribute("petTypes", petTypeService.findAllPetTypes());

        return "/pet/create";
    }

    @PostMapping({"/pet/create"})
    public String processCreatePetsForm(@ModelAttribute("pet") Pet pet, RedirectAttributes redirectAttributes ) {

        PetDto petDto = petService.createPetDtoFromEntity(pet);

        redirectAttributes.addAttribute("registrationSuccess", "Success");

        return "redirect:/pets";

    }


    // RETRIEVE

/*
    @GetMapping("/pet")
    public String getPets(Model model){
        List<PetDto> petDtos = petService.getAllPetDtos();
        model.addAttribute("title", "Pets");
        model.addAttribute("pets", petDtos);
        return "pets";
    }

    @GetMapping("/pet/{id}")
    public PetDto getPetById(@PathVariable Long id){
        return petService.getPetDtoById(id);
    }


*/



    // UPDATE


    @GetMapping({"/pet/update"})
    public String displayEditPetForm(Model model) {
        model.addAttribute("title", "Edit pets");
        model.addAttribute("pets", this.petService.getAllPetDtos());
        model.addAttribute("petTypes", petTypeService.findAllPetTypes());

        return "pet/update";
    }


    @GetMapping({"pet/update-details"})
    public String displayPetEditDetails(@RequestParam String name, Model model) {

        Pet pet = this.petService.getPetByName(name);

        if (pet == null) {
            model.addAttribute("title", "Invalid Petname:" + name);
        } else {
            model.addAttribute("title", pet.getName() + " Details");
            model.addAttribute("pet", pet);
            List<PetType> lst = petTypeService.findAllPetTypes();

            for(PetType p : lst){
                System.out.println("@@@@@@@@@@@@@@@@@@@ " + p.getId() + p.getName());
            }
            model.addAttribute("petTypes", petTypeService.findAllPetTypes());
        }


        return "pet/update-details";
    }

    @PostMapping({"pet/update-details"})
    public String processEditPetForm(@ModelAttribute("pet") Pet editedPet, Errors errors, Model model) {

        if (errors.hasErrors()) {

            model.addAttribute("title", "Edit Pet");
            return "pet/update-details";

        } else {

            Pet pet = petService.getPetByName(editedPet.getName());

            if(pet != null) {

                System.out.println("");
                pet.setWeight(editedPet.getWeight());
                pet.setAge(editedPet.getAge());
                pet.setPet_type(editedPet.getPet_type());
                this.petService.updatePetDtoFromEntity(pet);

            }
            return "redirect:/pets";
        }

    }



    // DELETE
    @GetMapping("/pet/delete")
    public String displayDeleteUserForm(Model model) {
        model.addAttribute("title", "Delete Pet");
        model.addAttribute("pets", this.petService.getAllPetDtos());
        return "pet/delete";
    }

    @PostMapping("/pet/delete")
    public String processDeleteUserForm(@ModelAttribute("name") String[] petNames) {

        if (petNames != null) {

            for(String name : petNames){

                Pet petOpt = petService.getPetByName(name);

                if(petOpt != null) {
                    this.petService.deletePet(petOpt);
                }
            }
        }

        return "redirect:/pets";
    }



    // ENDPOINTS


/*
    @GetMapping("/pet")

    public ResponseEntity<List<PetDto>> getAllPets() {
        List<PetDto> petDtos = petService.getAllPetDtos();

        System.out.println("pets...: ");

        for(PetDto p : petDtos){
            System.out.println(p.name());
        }
        return ResponseEntity.ok(petDtos);
    }
*/

    //ENDPOINTS


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
