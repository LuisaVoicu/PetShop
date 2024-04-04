package com.petshop.petshop.mappper.dto;

import com.petshop.petshop.model.Role;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserDto {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String login;
    private String token;
    private List<String> roles;
    private List<ProductDto> cartProducts;
    private String emailAddress;
    private String password;


}