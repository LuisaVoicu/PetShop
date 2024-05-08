package com.petshop.petshop.model;

import com.petshop.petshop.model.enums.Color;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "PETS")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    private Float weight;

    private Color color;

    private String imageurl;

    @ManyToOne
    private PetType pet_type;

    @ManyToOne
    private PetShop petShop;


}
