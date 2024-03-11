package com.petshop.petshop.service;

import com.petshop.petshop.mappper.RoleMapper;
import com.petshop.petshop.mappper.UserMapper;
import com.petshop.petshop.mappper.dto.UserDto;
import com.petshop.petshop.model.RegistrationRequest;
import com.petshop.petshop.model.User;
import com.petshop.petshop.repository.RoleRepository;
import com.petshop.petshop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final BCryptPasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;


    @Override
    public boolean checkEmail(String email) {
        return userRepository.existsByEmailAddress(email);
    }

    @Override
    public UserDto registerUser(RegistrationRequest registrationRequest) {
        User user = User.builder()
                .username(registrationRequest.getUsername())
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .password(registrationRequest.getPassword())
                .emailAddress(registrationRequest.getEmailAddress())
                .role((roleRepository.findByRole("USER")))
                .build();

        // nu se pot da String-uri  la cascadare si mapare

        return this.createUserDto(user);
    }

    @Override
    public UserDto getLoginUser(){
        return userMapper.userEntityToDto(
                userRepository.findLoginUser().orElse(null)
        );
    }

    @Override
    public UserDto getUserDtoById(Long id) {
        return userMapper.userEntityToDto(userRepository.findById(id).orElse(null));
    }

    @Override
    public User findUserByID(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserDto> getAllUserDtos() {
        return userMapper.userListEntityToDto(userRepository.findAll());
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    public UserDto createUserDto(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.userEntityToDto(userRepository.save(user));
    }

    @Override
    public UserDto updateUserDto(User user) {
        return userMapper.userEntityToDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
