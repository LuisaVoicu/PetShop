//package com.petshop.petshop.config.deleteThis;
//
//import com.petshop.petshop.model.User;
//import com.petshop.petshop.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//
//@RequiredArgsConstructor
//@Slf4j
//public class MyUserDetailsService  implements org.springframework.security.core.userdetails.UserDetailsService {
//    private final UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> userByUsername = userRepository.findByUsername(username);
//
//        if (userByUsername.isEmpty()) {
//            log.error("Could not find user with that username: {}", username);
//            throw new UsernameNotFoundException("Invalid credentials!");
//        }
//
//        User user = userByUsername.get();
//
//        if (!user.getUsername().equals(username)) {
//            log.error("Could not find user with that username: {}", username);
//            throw new UsernameNotFoundException("Invalid credentials!");
//        }
//
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        user.getRoles().stream()
//                .forEach(authority -> grantedAuthorities.add(new SimpleGrantedAuthority(authority.getRole())));
//
//        return new SecurityUser(user.getUsername(), user.getPassword(), true, true, true, true, grantedAuthorities,
//                user.getFirstName(), user.getLastName(), user.getEmailAddress(), user.getBirthdate());
//    }
//}
