package com.petshop.petshop.mappper.dto;

public record SignUpDto (String username, String emailAddress, String firstName, String lastName, String login,
                         String password) { }