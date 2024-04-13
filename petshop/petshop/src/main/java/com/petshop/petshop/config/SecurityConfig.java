package com.petshop.petshop.config;


/*import com.petshop.petshop.repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
*//*
        http
                .authorizeHttpRequests(authConfig -> {
                    authConfig.requestMatchers(HttpMethod.GET, "/", "/login", "/register", "/error", "/login-error", "/logout", "/css/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/createUser").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/user").hasAuthority("CUSTOMER");
                    authConfig.requestMatchers(HttpMethod.GET, "/admin").hasAuthority("ADMIN");
                    authConfig.requestMatchers(HttpMethod.GET, "/role").hasAuthority("ADMIN");
                    authConfig.requestMatchers(HttpMethod.GET, "/user").hasAnyAuthority("ADMIN", "CUSTOMER", "PET_FOSTER");
                    authConfig.requestMatchers(HttpMethod.GET, "/product").hasAnyAuthority("ADMIN", "CUSTOMER",  "PET_FOSTER", "SELLER");
                    authConfig.requestMatchers(HttpMethod.GET, "/products").hasAnyAuthority("ADMIN", "CUSTOMER", "PET_FOSTER", "SELLER");
                    authConfig.requestMatchers(HttpMethod.GET, "/category").hasAnyAuthority("ADMIN", "CUSTOMER", "PET_FOSTER", "SELLER");
                    authConfig.requestMatchers(HttpMethod.GET, "/categories").hasAnyAuthority("ADMIN", "CUSTOMER", "PET_FOSTER", "SELLER");
                    authConfig.requestMatchers(HttpMethod.GET, "/authorities").hasAnyAuthority("ADMIN");
                    authConfig.anyRequest().authenticated();
                })
                .formLogin(login -> {
                            login.loginPage("/login");
                            login.defaultSuccessUrl("/");
                            login.failureUrl("/login-error");
                        }
                )

                .logout(logout -> {
                    logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
                    logout.logoutSuccessUrl("/");
                    logout.deleteCookies("JSESSIONID");
                    logout.invalidateHttpSession(true);
                });
*//*
    http.authorizeHttpRequests(authConfig -> {
            authConfig.anyRequest().permitAll();}); // Allow all requests without authentication

        return http.build();
    }

    @Bean
    UserDetailsService myUserDetailsService(UserRepository userRepository) {
        return new MyUserDetailsService(userRepository);
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }

    @Bean
    ApplicationListener<AuthenticationSuccessEvent> successEvent() {
        return event -> {
            System.out.println("Success Login " + event.getAuthentication().getClass().getSimpleName() + " - " + event.getAuthentication().getName());
        };
    }

    @Bean
    ApplicationListener<AuthenticationFailureBadCredentialsEvent> failureEvent() {
        return event -> {
            System.err.println("Bad Credentials Login " + event.getAuthentication().getClass().getSimpleName() + " - " + event.getAuthentication().getName());
        };
    }
}*/


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

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http
               .exceptionHandling(customizer -> customizer.authenticationEntryPoint(userAuthenticationEntryPoint))
                .addFilterBefore(new JwtAuthFilter(userAuthenticationProvider), BasicAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

               .authorizeHttpRequests(authConfig -> {
                   authConfig.requestMatchers(HttpMethod.POST, "/login", "/register", "/logged", "/cart-product").permitAll();
                   authConfig.requestMatchers(HttpMethod.POST, "/category-delete", "/user-delete", "/user-edit","/category-edit").permitAll();//hasAuthority("ADMIN");
                   authConfig.requestMatchers(HttpMethod.POST, "/pet-create", "/pet-delete", "/pet-edit").permitAll();//hasAnyAuthority("ADMIN","FOSTER");
                   authConfig.requestMatchers(HttpMethod.POST, "/product-create", "/product-delete", "/product-edit").permitAll();//hasAnyAuthority("ADMIN","SELLER");
                   authConfig.requestMatchers(HttpMethod.GET, "/home").permitAll();
                   authConfig.requestMatchers(HttpMethod.GET, "/product","/category","/pet").permitAll();
                   authConfig.requestMatchers(HttpMethod.GET, "/user").permitAll();
                   authConfig.anyRequest().permitAll();
                //todo : aici trebuie sa pun conditia de authenticated
                   //authConfig.anyRequest().authenticated();
               })
        ;

/*
        http.authorizeHttpRequests(authConfig -> {
            authConfig.anyRequest().permitAll();}); // Allow all requests without authentication
*/

        return http.build();
    }
}