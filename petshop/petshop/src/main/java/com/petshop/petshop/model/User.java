package com.petshop.petshop.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NotFound;

import javax.validation.constraints.Size;
import java.time.LocalDate;
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
    private Integer id;

    private String username;

    private String password;

    private String emailAddress;

    private String lastName;

    private String firstName;

    private LocalDate birthdate;

    @Column(nullable = false)
    @Size(max = 100)
    private String login;

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


    public String toString(){
       return lastName+ " "+ firstName +  " " + emailAddress + " " + password;

    }
}
