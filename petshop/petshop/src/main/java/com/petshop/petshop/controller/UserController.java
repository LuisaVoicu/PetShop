package com.petshop.petshop.controller;

import com.petshop.petshop.controller.validation.GlobalExceptionHandlerController;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.*;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController extends GlobalExceptionHandlerController {

    private final UserService userService;
    private final RoleService roleService;
    private final ProductService productService;

    @GetMapping("/user")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtos = userService.getAllUserDtos();
        return ResponseEntity.ok(userDtos);
    }

    @PostMapping("/user-delete")
    public ResponseEntity<UserDto> deleteUser(@RequestBody(required = false) @Valid UserDto userDto) {
        if (userDto == null || userDto.getId() == null) {
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

        UserDto editedUser = userService.updateFromUserDto(userDto, user.getPassword());

        if (editedUser == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.created(URI.create("/user-edit" + editedUser.getId())).body(editedUser);
    }


    @PostMapping("/add-cart")
    public ResponseEntity<UserDto> addToCart(@RequestBody(required = false) @Valid CartDto cart) {

        if (cart.productId() == null || cart.productId() == null || cart.username() == null) {
            return ResponseEntity.badRequest().build();
        }

        Product product = productService.getProductById(cart.productId());
        Optional<User> user = userService.findByUsername(cart.username());

        if (!user.isPresent() || product == null) {
            return ResponseEntity.badRequest().build();
        }

        UserDto userSaved = userService.addCartProducts(user.get(), cart.productId());

        return ResponseEntity.created(URI.create("/add-cart" + userSaved.getId())).body(userSaved);
    }


    //todo : redenumeste cumva CartDto ca sa se potriveasca si cu Favs (ia de fapt id-prouct si username)
    @PostMapping("/add-fav")
    public ResponseEntity<UserDto> addToFavs(@RequestBody(required = false) @Valid CartDto fav) {

        if (fav.productId() == null || fav.productId() == null || fav.username() == null) {
            return ResponseEntity.badRequest().build();
        }

        Product product = productService.getProductById(fav.productId());
        Optional<User> user = userService.findByUsername(fav.username());

        if (!user.isPresent() || product == null) {
            return ResponseEntity.badRequest().build();
        }

        System.out.println("ADD TO fav!!!!" + user.get().getUsername() + " " + fav.productId() + " " + fav.productId() + "--" + fav.username());

        UserDto userSaved = userService.addFavProducts(user.get(), fav.productId());

        return ResponseEntity.created(URI.create("/add-fav" + userSaved.getId())).body(userSaved);
    }


    @PostMapping("/remove-fav")
    public ResponseEntity<UserDto> removedFromFavs(@RequestBody(required = false) @Valid CartDto fav) {

        if (fav.productId() == null || fav.productId() == null || fav.username() == null) {
            return ResponseEntity.badRequest().build();
        }

        Product product = productService.getProductById(fav.productId());
        Optional<User> user = userService.findByUsername(fav.username());

        if (!user.isPresent() || product == null) {
            return ResponseEntity.badRequest().build();
        }

        System.out.println("REMOVED FROM fav!!!!" + user.get().getUsername() + " " + fav.productId() + " " + fav.productId() + "--" + fav.username());

        UserDto userSaved = userService.removeFavProducts(user.get(), fav.productId());

        return ResponseEntity.created(URI.create("/remove-fav" + userSaved.getId())).body(userSaved);
    }

    @PostMapping("/cart-product")
    public ResponseEntity<List<ProductDto>> getCartProducts(@RequestBody(required = false) @Valid String username) {
        //todo -- get username from frontend without quotes

        String usernameTest = username.substring(1, username.length() - 1);

        List<ProductDto> productDtos = userService.fetchCartProducts(usernameTest);

        return ResponseEntity.ok(productDtos);
    }


    @PostMapping("/buy-product")
    public ResponseEntity<ReceiptDto> buyProducts(@RequestBody(required = false) @Valid String username) {
        //todo -- get username from frontend without quotes

        if (username == null) {
            return ResponseEntity.badRequest().build();
        }
        String usernameTest = username.substring(1, username.length() - 1);

        System.out.println("username: " + usernameTest);
        ReceiptDto receiptDto = userService.buyProducts(usernameTest);

        System.out.println("receipt:: " + receiptDto);
        return ResponseEntity.ok().body(receiptDto);
    }

    // roles assignment
    @PostMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<?> assignRoleToUser(@PathVariable Long userId, @PathVariable Long roleId) {
        userService.assignRoleToUser(userId, roleId);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/fav-prod")
    public ResponseEntity<List<ProductDto>> getFavProd(@RequestBody(required = false) @Valid String username) {
        //todo -- get username from frontend without quotes

        if (username == null) {
            return ResponseEntity.badRequest().build();
        }
        String usernameTest = username.substring(1, username.length() - 1);

        System.out.println("username: " + usernameTest);
        List<ProductDto> productDtos = userService.fetchFavProducts(usernameTest);

        System.out.println("products" + productDtos);
        return ResponseEntity.ok(productDtos);
    }


}