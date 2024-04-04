package com.petshop.petshop.controller;


import com.petshop.petshop.config.UserAuthenticationProvider;
import com.petshop.petshop.mappper.dto.CredentialsDto;
import com.petshop.petshop.mappper.dto.SignUpDto;
import com.petshop.petshop.mappper.dto.UserDto;
import com.petshop.petshop.model.RegistrationRequest;
import com.petshop.petshop.model.User;
import com.petshop.petshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;
    private final UserAuthenticationProvider userAuthenticationProvider;

/*
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("title", "Login");

        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model){
        model.addAttribute("title", "Login");
        model.addAttribute("loginError", true);

        return "login";
    }
*/


    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid CredentialsDto credentialsDto) {

        System.out.println("HERE I AM ");
        System.out.println("PAROLA LUATA:"+credentialsDto.password());
        UserDto userDto = userService.login(credentialsDto);
        System.out.println("hello?!");

        userDto.setToken(userAuthenticationProvider.createToken(userDto));
        System.out.println("done!");
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid SignUpDto user) {
        UserDto createdUser = userService.register(user);
        createdUser.setToken(userAuthenticationProvider.createToken(createdUser));
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
    }

    @PostMapping("/logged")
    public ResponseEntity<UserDto> logged(@RequestBody(required = false) @Valid String username) {

        //todo -- get username from frontend without quotes

        String usernameTest = username.substring(1, username.length()-1);

        if (username == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<User> userOpt = userService.findByUsername(usernameTest);

        System.out.println(userOpt.toString());
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        UserDto userDto = userService.createUserDto(userOpt.get());

        return ResponseEntity.ok().body(userDto);
    }

}