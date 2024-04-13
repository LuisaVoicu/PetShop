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

/*
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
                   authConfig.requestMatchers(HttpMethod.POST,"/api/login","/api/register").permitAll();
                   authConfig.requestMatchers(HttpMethod.POST, "/api/category-delete", "/api/user-delete", "/api/user-edit","/api/category-edit").hasAuthority("ADMIN");
                   authConfig.anyRequest().permitAll();


               })

       ;

        return http.build();
    }
}


 */


import com.petshop.petshop.filter.JwtAuthenticationFilter;
import com.petshop.petshop.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsServiceImp;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final CustomLogoutHandler logoutHandler;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        req->req.requestMatchers("/login/**","/register/**")
                                .permitAll()
                                .requestMatchers("/admin_only/**").hasAuthority("ADMIN")
                                .requestMatchers("/user/**").hasAuthority("ADMIN")
                                .requestMatchers("/pet/**").hasAuthority("SELLER")
                                .anyRequest().authenticated()
                ).userDetailsService(userDetailsServiceImp)
                .sessionManagement(session->session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(
                        e->e.accessDeniedHandler(
                                        (request, response, accessDeniedException)->response.setStatus(403)
                                )
                                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .logout(l->l
                        .logoutUrl("/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()
                        ))
                .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


}