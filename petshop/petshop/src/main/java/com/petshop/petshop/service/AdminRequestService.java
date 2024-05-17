package com.petshop.petshop.service;

import com.petshop.petshop.mappper.dto.AdminRequestDto;
import com.petshop.petshop.model.AdminRequest;
import com.petshop.petshop.model.User;
import com.petshop.petshop.model.enums.RequestType;

import java.util.List;

public interface AdminRequestService {

    List<AdminRequestDto> getAllAdminRequests();
    List<AdminRequestDto> getAdminRequestsByUsername(String username);
    AdminRequest createAdminRequest(User user, String requestType);

    void deleteAdminRequestByUserAndRequestType(User user , String requestType);
    void changeRequestStatus(User user , String requestType, String status);
}
