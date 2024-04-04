package com.petshop.petshop.controller;

import com.petshop.petshop.mappper.dto.*;
import com.petshop.petshop.model.Pet;
import com.petshop.petshop.model.Product;
import com.petshop.petshop.model.RegistrationRequest;
import com.petshop.petshop.model.User;
import com.petshop.petshop.service.ProductService;
import com.petshop.petshop.service.RoleService;
import com.petshop.petshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.*;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final ProductService productService;

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
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtos = userService.getAllUserDtos();
        return ResponseEntity.ok(userDtos);
    }




    @PostMapping("/user-delete")
    public ResponseEntity<UserDto> deleteUser(@RequestBody(required = false) @Valid UserDto userDto) {
        if (userDto == null ||userDto.getId() == null) {
            // Handle case when product or product ID is missing
            return ResponseEntity.badRequest().build();
        }

        User deletedUser = userService.findUserByID(userDto.getId());

        if (deletedUser != null) {
            userService.deleteUser(deletedUser);
            return ResponseEntity.ok().body(userDto); // Product successfully deleted
        } else {
            return ResponseEntity.notFound().build(); // Product not found
        }
    }


    @PostMapping("/user-edit")
    public ResponseEntity<UserDto> editUser(@RequestBody(required = false) @Valid UserDto userDto) {

        if (userDto == null || userDto.getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        User user = userService.findUserByID(userDto.getId());

        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        UserDto editedUser = userService.updateFromUserDto(userDto,user.getPassword());

        if(editedUser==null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.created(URI.create("/user-edit" + editedUser.getId())).body(editedUser);
    }



/*
    @PostMapping("/shopping")
    public ResponseEntity<UserDto> addToShoppingCart(@RequestBody(required = false) @Valid UserDto userDto, @RequestBody(required = false) @Valid ProductDto productDto) {


        if (userDto == null || userDto.getId() == null) {
            // Handle case when product or product ID is missing
            return ResponseEntity.badRequest().build();
        }

        User user = userService.addProductToCart(userDto, productDto);


        if (user != null) {
            return ResponseEntity.ok().body(userDto); // Product successfully deleted
        } else {
            return ResponseEntity.notFound().build(); // Product not found
        }
    }
*/


    @PostMapping("/add-cart")
    public ResponseEntity<UserDto> addToCart(@RequestBody(required = false) @Valid CartDto cart) {

       if (cart.productId() == null || cart.productId()== null || cart.username() == null) {
            // Handle case when product or product ID is missing
            return ResponseEntity.badRequest().build();
        }

        Product product = productService.getProductById(cart.productId());
        Optional<User> user = userService.findByUsername(cart.username());

        if(!user.isPresent() || product == null){
            return ResponseEntity.badRequest().build();
        }

        System.out.println("ADD TO CART!!!!" + user.get().getUsername() + " "+  cart.productId()+ " " + cart.productId() + "--" + cart.username());


        UserDto userSaved = userService.addCartProducts(user.get(), cart.productId());


        return ResponseEntity.created(URI.create("/add-cart" + userSaved.getId())).body(userSaved);
    }


    @PostMapping("/cart-product")
    public ResponseEntity<List<ProductDto>> getCartProducts(@RequestBody(required = false) @Valid String username) {
        //todo -- get username from frontend without quotes

        String usernameTest = username.substring(1, username.length()-1);

        System.out.println("username: " + usernameTest);
        List<ProductDto> productDtos = userService.fetchCartProducts(usernameTest);

        System.out.println("products"+productDtos);
        return ResponseEntity.ok(productDtos);
    }


    @PostMapping("/buy-product")
    public ResponseEntity<ReceiptDto> buyProducts(@RequestBody(required = false) @Valid String username) {
        //todo -- get username from frontend without quotes

        String usernameTest = username.substring(1, username.length()-1);

        System.out.println("username: " + usernameTest);
        ReceiptDto receiptDto = userService.buyProducts(usernameTest);

        System.out.println("receipt"+receiptDto);
        return ResponseEntity.ok(receiptDto);
    }

    // roles assignment
    @PostMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<?> assignRoleToUser(@PathVariable Long userId, @PathVariable Long roleId) {
        userService.assignRoleToUser(userId, roleId);
        return ResponseEntity.ok().build();
    }


}