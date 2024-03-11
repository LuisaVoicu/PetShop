package com.petshop.petshop.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "PETSHOPS")
public class PetShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nume;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Pet> pets;

    @OneToMany
    private List<User> users;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Category> categories;


}
