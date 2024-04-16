package com.petshop.petshop.mappper;

import com.petshop.petshop.mappper.dto.SignUpDto;
import com.petshop.petshop.mappper.dto.UserDto;
import com.petshop.petshop.model.Role;
import com.petshop.petshop.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class UserMapper {

    private final RoleMapper roleMapper;
    private final ProductMapper productMapper;

    public UserDto userEntityToDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email_address(user.getEmail_address())
                .roles(roleMapper.roleListEntityToDto(user.getRoles()))
               // .cartProducts(productMapper.productListEntitytoDto(user.getCartProducts()))
                //.favouriteProducts(productMapper.productListEntitytoDto(user.getFavouriteProducts()))
                .token(user.getToken())
                .imageurl(user.getImageurl())
                .loginTime(user.getLoginTime())
                .build();
    }

    public List<UserDto> userListEntityToDto(List<User> users){
        return users.stream()
                .map(user -> userEntityToDto(user))
                .toList();
    }

    public User userDtoToEntity(UserDto userDto, String password){

        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .password(password)
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email_address(userDto.getEmail_address())
                .roles(roleMapper.roleListDtoToEntity(userDto.getRoles()))
                .cart_products(productMapper.productListDtoToEntity(userDto.getCart_products()))
                .favorite(productMapper.productListDtoToEntity(userDto.getFavorite()))
                .token(userDto.getToken())
                .imageurl(userDto.getImageurl())
                .loginTime(userDto.getLoginTime())
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
        user.setUsername(signUpDto.getUsername());
        user.setPassword(signUpDto.getPassword());
        user.setFirstName(signUpDto.getFirstName());
        user.setLastName(signUpDto.getLastName());
        user.setEmail_address(signUpDto.getEmail_address());
        return user;
    }



}