package com.petshop.petshop.service;

import com.petshop.petshop.mappper.dto.AdminRequestDto;
import com.petshop.petshop.model.AdminRequest;
import com.petshop.petshop.model.User;
import com.petshop.petshop.model.enums.RequestType;

import java.util.List;

public interface AdminRequestService {

    List<AdminRequestDto> getAllAdminRequests();
    List<AdminRequest> getAdminRequestsByUserId(Long id);
    AdminRequest createAdminRequest(User user, String requestType);

    void deleteAdminRequestByUserAndRequestType(User user , String requestType);
}
