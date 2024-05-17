package com.petshop.petshop.service.impl;


import com.petshop.petshop.mappper.AdminRequestMapper;
import com.petshop.petshop.mappper.dto.AdminRequestDto;
import com.petshop.petshop.model.AdminRequest;
import com.petshop.petshop.model.User;
import com.petshop.petshop.model.enums.RequestStatus;
import com.petshop.petshop.model.enums.RequestType;
import com.petshop.petshop.repository.AdminRequestRepository;
import com.petshop.petshop.repository.UserRepository;
import com.petshop.petshop.service.AdminRequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminRequestServiceImpl  implements AdminRequestService {

    private AdminRequestRepository adminRequestRepository;
    private UserRepository userRepository;
    private AdminRequestMapper adminRequestMapper;

    @Override
    public List<AdminRequestDto> getAllAdminRequests() {
        List<AdminRequest> adminRequests =  adminRequestRepository.findAll();
        return adminRequestMapper.adminRequestListEntityToDto(adminRequests);
    }

    @Override
    public List<AdminRequestDto> getAdminRequestsByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            return null;
        }

        List<AdminRequest> adminRequests =  adminRequestRepository.findByUser(user.get());

        return adminRequestMapper.adminRequestListEntityToDto(adminRequests);
    }

    @Override
    public AdminRequest createAdminRequest(User user, String requestType) {

        if(requestAlreadyExists(user,requestType)){
            return null;
        }


        AdminRequest adminRequest = AdminRequest.builder()
                .user(user)
                .request(requestType)
                .status("PENDING")
                .build();

        adminRequestRepository.save(adminRequest);

        return adminRequest;
    }

    @Override
    public void deleteAdminRequestByUserAndRequestType(User user , String requestType) {
        AdminRequest adminRequest = adminRequestRepository.findByUserAndRequest(user, requestType);

        System.out.println(user.getUsername()+ " ==== " + requestType);
        if(adminRequest != null){
            adminRequestRepository.delete(adminRequest);
        }
        else{
            System.out.println("not found!!!!");
        }
    }

    @Override
    public void changeRequestStatus(User user , String requestType, String status) {
        AdminRequest adminRequest = adminRequestRepository.findByUserAndRequest(user, requestType);
        adminRequest.setStatus(status);
        if(adminRequest != null){
            adminRequestRepository.save(adminRequest);
        }
    }

    private boolean requestAlreadyExists(User user, String requestType){

        AdminRequest adminRequest = adminRequestRepository.findByUserAndRequest(user,requestType);
        if(adminRequest != null){
            System.out.println("AdminRequest already exists!");
            return true;
        }
        return false;
    }
}
