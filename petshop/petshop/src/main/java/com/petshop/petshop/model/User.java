package com.petshop.petshop.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NotFound;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String emailAddress;

    private String lastName;

    private String firstName;

    private LocalDate birthdate;

    @Column(nullable = false)
    @Size(max = 100)
    private String login;

    private String token;



    @Singular
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "users_roles",
            joinColumns = {
                    @JoinColumn(name = "USERS_ID",
                            referencedColumnName = "ID")},
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLES_ID",
                            referencedColumnName = "ID")})
    private List<Role> roles;

    @Column(nullable = true)
    @OneToMany(mappedBy = "seller")
    private List<Product> saleProducts;

    @Column(nullable = true)
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_favorite",
            joinColumns = {
                    @JoinColumn(name = "USERS_ID",
                            referencedColumnName = "ID")},
            inverseJoinColumns = {
                    @JoinColumn(name = "PRODUCTS_ID",
                            referencedColumnName = "ID")})
    private List<Product> favouriteProducts;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_cart_products",
            joinColumns = {
                    @JoinColumn(name = "USER_ID",
                            referencedColumnName = "ID")},
            inverseJoinColumns = {
                    @JoinColumn(name = "PRODUCT_ID",
                            referencedColumnName = "ID")})
    private List<Product> cartProducts;

    private LocalDateTime loginTime;
    private String imageurl;

    public String toString(){
       return lastName+ " "+ firstName +  " " + emailAddress + " " + password;

    }
}
