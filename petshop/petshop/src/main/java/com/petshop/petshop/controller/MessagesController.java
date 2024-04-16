package com.petshop.petshop.controller;

import com.petshop.petshop.controller.validation.GlobalExceptionHandlerController;
import com.petshop.petshop.mappper.dto.UserDto;
import com.petshop.petshop.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
public class MessagesController extends GlobalExceptionHandlerController {

    @GetMapping("/messages")
    public ResponseEntity<List<String>> messages() {
        return ResponseEntity.ok(Arrays.asList("first", "second"));
    }


}