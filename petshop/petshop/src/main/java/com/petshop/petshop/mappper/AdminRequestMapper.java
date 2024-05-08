package com.petshop.petshop.mappper;


import com.petshop.petshop.mappper.dto.AdminRequestDto;
import com.petshop.petshop.mappper.dto.CategoryDto;
import com.petshop.petshop.model.AdminRequest;
import com.petshop.petshop.model.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class AdminRequestMapper {


    public AdminRequestDto adminRequestEntityToDto(AdminRequest adminRequest){
        return AdminRequestDto
                .builder()
                .username(adminRequest.getUser().getUsername())
                .status(adminRequest.getStatus())
                .request(adminRequest.getRequest())
                .build();
    }

    public List<AdminRequestDto> categoryListEntityToDto(List<AdminRequest> adminRequestList){
        return adminRequestList.stream()
                .map(category -> adminRequestEntityToDto(category))
                .toList();
    }


}
