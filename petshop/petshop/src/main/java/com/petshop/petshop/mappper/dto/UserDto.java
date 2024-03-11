package com.petshop.petshop.mappper.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record UserDto(String username, List<RoleDto> roles, String firstName, String lastName, String emailAddress, LocalDate birthdate) {
}
