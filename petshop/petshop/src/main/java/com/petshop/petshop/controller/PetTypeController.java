package com.petshop.petshop.controller;

import com.petshop.petshop.mappper.dto.PetTypeDto;
import com.petshop.petshop.service.PetTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PetTypeController {


    private final PetTypeService petTypeService;

    @GetMapping("/petTypes")
    public String getPetTypes(Model model){
        List<PetTypeDto> petTypes = petTypeService.getAllPetTypeDtos();

        model.addAttribute("title", "PetTypes");
        model.addAttribute("petTypes", petTypes);

        return "petTypes";
    }

    @GetMapping("/petTypes/{id}")
    public PetTypeDto getPetTypeById(@PathVariable Long id){
        return petTypeService.getPetTypeDtoById(id);
    }
}
