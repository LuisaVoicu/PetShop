package com.petshop.petshop.mappper.dto;

import com.petshop.petshop.model.enums.RequestType;
import lombok.Builder;

import java.util.List;

@Builder
public record AdminRequestDto(String status, String username, String request) {
}
