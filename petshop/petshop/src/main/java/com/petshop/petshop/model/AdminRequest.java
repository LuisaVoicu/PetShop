package com.petshop.petshop.model;


import com.petshop.petshop.model.enums.RequestStatus;
import com.petshop.petshop.model.enums.RequestType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "ADMIN_REQUEST")
public class AdminRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String request;

    private String status;

}
