package com.petshop.petshop.service;

import com.petshop.petshop.mappper.dto.UserDto;
import com.petshop.petshop.model.RegistrationRequest;
import com.petshop.petshop.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    boolean checkEmail(String email);

    //todo
    UserDto registerUser(RegistrationRequest registrationRequest);

    UserDto getLoginUser();

    UserDto getUserDtoById(Long id);

    User findUserByID(Long id);

    List<UserDto> getAllUserDtos();

    Optional<User> findByUsername(String username);

    UserDto createUserDto(User user);

    UserDto updateUserDto(User user);

    void deleteUser(User user);
}