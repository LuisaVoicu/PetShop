package com.petshop.petshop.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;
    private final UserAuthenticationProvider userAuthenticationProvider;

/*
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http
               .exceptionHandling(customizer -> customizer.authenticationEntryPoint(userAuthenticationEntryPoint))
               .addFilterBefore(new JwtAuthFilter(userAuthenticationProvider), BasicAuthenticationFilter.class)
               .csrf(AbstractHttpConfigurer::disable)
               .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

//               .authorizeHttpRequests(authConfig -> {
//                   authConfig.requestMatchers(HttpMethod.POST, "/login", "/register", "/logged", "/cart-product").permitAll();
//                   authConfig.requestMatchers(HttpMethod.POST, "/category-delete", "/user-delete", "/user-edit","/category-edit").permitAll();//hasAuthority("ADMIN");
//                   authConfig.requestMatchers(HttpMethod.POST, "/pet-create", "/pet-delete", "/pet-edit").permitAll();//hasAnyAuthority("ADMIN","FOSTER");
//                   authConfig.requestMatchers(HttpMethod.POST, "/product-create", "/product-delete", "/product-edit").permitAll();//hasAnyAuthority("ADMIN","SELLER");
//                   authConfig.requestMatchers(HttpMethod.GET, "/home").permitAll();
//                   authConfig.requestMatchers(HttpMethod.GET, "/product","/category","/pet").permitAll();
//                   authConfig.requestMatchers(HttpMethod.GET, "/user").permitAll();
//                   authConfig.anyRequest().permitAll();
//                //todo : aici trebuie sa pun conditia de authenticated
//                   //authConfig.anyRequest().authenticated();
//               })



               .authorizeHttpRequests(authConfig -> {
                   authConfig.requestMatchers(HttpMethod.POST,"/login","/register").permitAll();
//                  authConfig.requestMatchers(HttpMethod.POST, "/category-delete", "/user-delete", "/user-edit","/category-edit").hasAuthority("ADMIN");
//                   authConfig.requestMatchers(HttpMethod.POST, "/logged", "/product").hasAuthority("CUSTOMER");
                   authConfig.anyRequest().authenticated();

               })

       ;
        return http.build();
    }
*/
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .exceptionHandling(customizer -> customizer.authenticationEntryPoint(userAuthenticationEntryPoint))
                .addFilterBefore(new JwtAuthFilter(userAuthenticationProvider), BasicAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(HttpMethod.POST, "/login", "/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/logged").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/user").hasAuthority("CUSTOMER")
                        .anyRequest().authenticated())
        ;
        return http.build();
    }
}