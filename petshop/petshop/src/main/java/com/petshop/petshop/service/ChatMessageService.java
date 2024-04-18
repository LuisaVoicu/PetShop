package com.petshop.petshop.service;

import com.petshop.petshop.model.Message;
import com.petshop.petshop.model.User;

import java.util.List;

public interface ChatMessageService {
    List<Message> getMessagesByUser(User user);
}
