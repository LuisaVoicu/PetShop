package com.petshop.petshop.service.impl;

import com.petshop.petshop.exception.AppException;
import com.petshop.petshop.mappper.RoleMapper;
import com.petshop.petshop.mappper.UserMapper;
import com.petshop.petshop.mappper.dto.CredentialsDto;
import com.petshop.petshop.mappper.dto.SignUpDto;
import com.petshop.petshop.mappper.dto.UserDto;
import com.petshop.petshop.model.RegistrationRequest;
import com.petshop.petshop.model.Role;
import com.petshop.petshop.model.User;
import com.petshop.petshop.repository.RoleRepository;
import com.petshop.petshop.repository.UserRepository;
import com.petshop.petshop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

   // private final BCryptPasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    private final PasswordEncoder passwordEncoder;


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
    public UserDto updateFromUserDto(UserDto userDto, String password) {
        User user = userMapper.userDtoToEntity(userDto, password);
      //  System.out.println("###########\n"+user.toString());
        //user.setLogin("A");
        System.out.println("~~~~~~~~~~~~~~>>>>>" + user.getId());
       User saved = userRepository.save(user);
       return userDto;
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }


    public UserDto login(CredentialsDto credentialsDto) {

        User user = userRepository.findByUsername(credentialsDto.login())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), user.getPassword())) {
            return userMapper.userEntityToDto(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto register(SignUpDto userDto) {
        Optional<User> optionalUser = userRepository.findByUsername(userDto.username());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.signUpToUser(userDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.password())));

        //todo - set register by login(?)  not by username

        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByRole("CUSTOMER"));
     //   user.setRoles(roles);
        user.setLogin("dummy "+user.getUsername());

        User savedUser = userRepository.save(user);

        return userMapper.userEntityToDto(savedUser);
    }

    public UserDto findByLogin(String login) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.userEntityToDto(user);
    }

}
