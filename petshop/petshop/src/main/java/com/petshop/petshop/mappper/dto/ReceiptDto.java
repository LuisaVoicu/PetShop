package com.petshop.petshop.mappper.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ReceiptDto {
    private String firstName;
    private String lastName;
    private String username;
    private List<String> product;
    private String date;

}