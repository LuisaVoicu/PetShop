package com.petshop.petshop.service.impl;

import com.petshop.petshop.model.ChatMessage;
import com.petshop.petshop.model.Message;
import com.petshop.petshop.model.User;
import com.petshop.petshop.repository.ChatMessageRepository;
import com.petshop.petshop.service.ChatMessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {

    ChatMessageRepository messageRepository;

    @Override
    public List<Message> getMessagesByUser(User user) {
        if(user == null){
            return null;
        }

        ChatMessage chatMessage = user.getChatMessage();

        if(chatMessage == null){
            return null;
        }

        List<Message> messages = chatMessage.getMessages();

        return  messages;
    }
}
