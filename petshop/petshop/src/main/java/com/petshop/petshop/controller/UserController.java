package com.petshop.petshop.controller;

import com.petshop.petshop.mappper.dto.PetDto;
import com.petshop.petshop.mappper.dto.UserDto;
import com.petshop.petshop.model.Pet;
import com.petshop.petshop.model.RegistrationRequest;
import com.petshop.petshop.model.User;
import com.petshop.petshop.service.RoleService;
import com.petshop.petshop.service.UserService;
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
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleService roleService;


    // CREATE
    @GetMapping({"/user/create"})
    public String displayCreateUserForm(@RequestParam(value="registrationSuccess", required = false) String success, Model model) {

        model.addAttribute("title", "Register");
        model.addAttribute("registrationSuccess", success);
        model.addAttribute("user", new RegistrationRequest());
        model.addAttribute("roles", roleService.getAllRoles());

        return "/user/create";
    }

    @PostMapping({"/user/create"})
    public String processCreateUsersForm(@ModelAttribute("user") RegistrationRequest registrationRequest, RedirectAttributes redirectAttributes ) {

        UserDto userDto = userService.registerUser(registrationRequest);

        redirectAttributes.addAttribute("registrationSuccess", "Success");

        return "redirect:/users";

    }


    // RETRIEVE

/*
    @GetMapping("/user")
    public String getUsers(Model model){
        List<UserDto> userDtos = userService.getAllUserDtos();
        model.addAttribute("title", "Users");
        model.addAttribute("users", userDtos);
        return "users";
    }
*/

    @GetMapping("/user/{id}")
    public UserDto getUserById(@PathVariable Long id){
        return userService.getUserDtoById(id);
    }


    // UPDATE


    @GetMapping({"/user/update"})
    public String displayEditUserForm(Model model) {
        model.addAttribute("title", "Edit users");
        model.addAttribute("users", this.userService.getAllUserDtos());
        return "user/update";
    }


    @GetMapping({"user/update-details"})
    public String displayUserEditDetails(@RequestParam String username, Model model) {
        Optional<User> userOpt = this.userService.findByUsername(username);
        if (userOpt.isEmpty()) {
            model.addAttribute("title", "Invalid Username:" + username);
        } else {
            User user = (User)userOpt.get();
            model.addAttribute("title", user.getUsername() + " Details");
            model.addAttribute("user", user);
            model.addAttribute("roles", roleService.getAllRoles());

        }


        return "user/update-details";
    }

    @PostMapping({"user/update-details"})
    public String processEditUserForm(@ModelAttribute("user") User editedUser, Errors errors, Model model) {

        if (errors.hasErrors()) {

            model.addAttribute("title", "Edit User");
            return "user/update-details";
        } else {

            Optional<User> userOpt = userService.findByUsername(editedUser.getUsername());

            if(!userOpt.isEmpty()) {

                userOpt.get().setFirstName(editedUser.getFirstName());
                userOpt.get().setLastName(editedUser.getLastName());
                userOpt.get().setEmailAddress(editedUser.getEmailAddress());
                userOpt.get().setRoles(editedUser.getRoles());
                this.userService.updateUserDto(userOpt.get());
            }
            return "redirect:/users";
        }

    }



    // DELETE
    @GetMapping("/user/delete")
    public String displayDeleteUserForm(Model model) {
        model.addAttribute("title", "Delete User");
        model.addAttribute("users", this.userService.getAllUserDtos());
        return "user/delete";
    }

    @PostMapping("/user/delete")
    public String processDeleteUserForm(@ModelAttribute("userUsernames") String[] userUsernames) {

        if (userUsernames != null) {

            for(String username : userUsernames){

                Optional<User> userOpt = userService.findByUsername(username);

                if(!userOpt.isEmpty()) {
                    this.userService.deleteUser(userOpt.get());
                }
            }
        }

        return "redirect:/users";
    }


    // ENDPOINTS

    @GetMapping("/user")

    public ResponseEntity<List<UserDto>> getAllProducts() {
        List<UserDto> userDtos = userService.getAllUserDtos();
        return ResponseEntity.ok(userDtos);
    }



    @PostMapping("/user-delete")
    public ResponseEntity<UserDto> deleteUser(@RequestBody(required = false) @Valid UserDto userDto) {
        if (userDto == null ) {
            // Handle case when product or product ID is missing
            return ResponseEntity.badRequest().build();
        }

        Optional<User> deletedUser = userService.findByUsername(userDto.getUsername());

        if (deletedUser.isPresent()) {
            userService.deleteUser(deletedUser.get());
            return ResponseEntity.ok().body(userDto); // Product successfully deleted
        } else {
            return ResponseEntity.notFound().build(); // Product not found
        }
    }


    @PostMapping("/user-edit")
    public ResponseEntity<UserDto> editUser(@RequestBody(required = false) @Valid UserDto userDto) {

        Optional<User> user = userService.findByUsername(userDto.getUsername());

        System.out.println(userDto.getUsername());


        if (!user.isPresent()) {
            // Handle case when product or product ID is missing
            return ResponseEntity.badRequest().build();
        }

        System.out.println("IM HEREEEEEEEEEEEEEEE!!!!!!!!!!!!!!!!!!!!!! 1. " + user.get().getFirstName());
        System.out.println("IM HEREEEEEEEEEEEEEEE!!!!!!!!!!!!!!!!!!!!!! 2. " + userDto.getFirstName());

        System.out.println("pass:"+user.get().getPassword()+"<- firstname:"+user.get().getFirstName());

        UserDto editedUser = userService.updateFromUserDto(userDto, user.get().getPassword());

        System.out.println("IM HEREEEEEEEEEEEEEEE---AAAA!!!!!!!!!!!!!!!!!!!!!! " + userDto.getFirstName());


        return ResponseEntity.created(URI.create("/user-edit" + editedUser.getUsername())).body(editedUser);
    }

}