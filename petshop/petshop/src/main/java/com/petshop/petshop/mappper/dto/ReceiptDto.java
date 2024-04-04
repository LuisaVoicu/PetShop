package com.petshop.petshop.mappper.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public record ReceiptDto (String firstName, String lastName, String username, List<ProductDto> product, LocalDate date){


}