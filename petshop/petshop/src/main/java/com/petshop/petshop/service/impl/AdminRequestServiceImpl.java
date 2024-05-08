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
        return adminRequestMapper.categoryListEntityToDto(adminRequests);
    }

    @Override
    public List<AdminRequest> getAdminRequestsByUserId(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            return null;
        }
        return user.get().getAdminRequestS();
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
        if(adminRequest != null){
            adminRequestRepository.delete(adminRequest);
        }
    }


    private boolean requestAlreadyExists(User user, String requestType){

        AdminRequest adminRequest = adminRequestRepository.findByUserAndRequest(user,requestType);
        if(adminRequest != null){
            return true;
        }
        return false;
    }
}
