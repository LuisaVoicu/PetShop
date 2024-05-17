package com.petshop.petshop.repository;

import com.petshop.petshop.model.AdminRequest;
import com.petshop.petshop.model.User;
import com.petshop.petshop.model.enums.RequestType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface AdminRequestRepository extends CrudRepository<AdminRequest,Long> {


    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"id"})
    List<AdminRequest> findAll();


    AdminRequest findByUserAndRequest(User user, String request);

    List<AdminRequest> findByUser(User user);

}
