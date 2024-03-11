package com.petshop.petshop.controller;

import com.petshop.petshop.mappper.dto.PetDto;
import com.petshop.petshop.model.Pet;
import com.petshop.petshop.model.PetType;
import com.petshop.petshop.service.PetService;
import com.petshop.petshop.service.PetTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;
    private final PetTypeService petTypeService;



    // CREATE
    @GetMapping({"/pets/create"})
    public String displayCreatePetForm(Model model) {

        //  model.addAttribute("title", "Register");
        model.addAttribute("pet", new Pet());
        model.addAttribute("petTypes", petTypeService.findAllPetTypes());

        return "/pet/create";
    }

    @PostMapping({"/pets/create"})
    public String processCreatePetsForm(@ModelAttribute("pet") Pet pet, RedirectAttributes redirectAttributes ) {

        PetDto petDto = petService.createPetDtoFromEntity(pet);

        redirectAttributes.addAttribute("registrationSuccess", "Success");

        return "redirect:/pets";

    }


    // RETRIEVE

    @GetMapping("/pets")
    public String getPets(Model model){
        List<PetDto> petDtos = petService.getAllPetDtos();
        model.addAttribute("title", "Pets");
        model.addAttribute("pets", petDtos);
        return "pets";
    }

    @GetMapping("/pets/{id}")
    public PetDto getPetById(@PathVariable Long id){
        return petService.getPetDtoById(id);
    }





    // UPDATE


    @GetMapping({"/pets/update"})
    public String displayEditPetForm(Model model) {
        model.addAttribute("title", "Edit pets");
        model.addAttribute("pets", this.petService.getAllPetDtos());
        return "pet/update";
    }


    @GetMapping({"pets/update-details"})
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

    @PostMapping({"pets/update-details"})
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
                pet.setPetType(editedPet.getPetType());
                this.petService.updatePetDtoFromEntity(pet);

            }
            return "redirect:/pets";
        }

    }



    // DELETE
    @GetMapping("/pets/delete")
    public String displayDeleteUserForm(Model model) {
        model.addAttribute("title", "Delete Pet");
        model.addAttribute("pets", this.petService.getAllPetDtos());
        return "pet/delete";
    }

    @PostMapping("/pets/delete")
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

}
