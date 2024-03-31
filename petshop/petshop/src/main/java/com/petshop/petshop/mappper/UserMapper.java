package com.petshop.petshop.mappper;

import com.petshop.petshop.mappper.dto.SignUpDto;
import com.petshop.petshop.mappper.dto.UserDto;
import com.petshop.petshop.model.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class UserMapper {

    private final RoleMapper roleMapper;

    public UserDto userEntityToDto(User user){
        return UserDto.builder()
                .username(user.getUsername())
                .roles(roleMapper.roleListEntityToDto(user.getRoles()))
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .emailAddress(user.getEmailAddress())
                .login(user.getLogin())
                //.birthdate(user.getBirthdate())
                .build();
    }

    public List<UserDto> userListEntityToDto(List<User> users){
        return users.stream()
                .map(user -> userEntityToDto(user))
                .toList();
    }

    public User userDtoToEntity(UserDto userDto, String password){
        return User.builder()
                .username(userDto.getUsername())
                .password(password)
                .roles(roleMapper.roleListDtoToEntity(userDto.getRoles()))
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .emailAddress(userDto.getEmailAddress())
                //.birthdate(userDto.getBirthdate())
                .build();
    }

    public List<User> userListDtoToEntity(List<UserDto> userDtos, String password){
        return userDtos.stream()
                .map(userDto -> userDtoToEntity(userDto, password))
                .toList();
    }

/*
    public abstract UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    public abstract User signUpToUser(SignUpDto signUpDto);
*/

    public User signUpToUser(SignUpDto signUpDto) {
        if (signUpDto == null) {
            return null;
        }

        User user = new User();
        user.setUsername(signUpDto.username());
        user.setPassword(signUpDto.password());
        user.setFirstName(signUpDto.firstName());
        user.setLastName(signUpDto.lastName());
        user.setEmailAddress(signUpDto.emailAddress());
        return user;
    }

}