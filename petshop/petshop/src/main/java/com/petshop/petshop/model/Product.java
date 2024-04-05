package com.petshop.petshop.model;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;
    private String imageURL;
    private Double price;
    @ManyToOne
    private Category category;

    @ManyToOne
    private User seller;



}
