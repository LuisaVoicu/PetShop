package com.petshop.petshop.repository;

import com.petshop.petshop.model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends CrudRepository<Message, Long> {

    List<Message> findAll();

}
