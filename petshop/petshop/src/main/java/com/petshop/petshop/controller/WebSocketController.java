package com.petshop.petshop.controller;

import com.petshop.petshop.mappper.dto.ChatMessageDto;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/{roomId}")
    public ChatMessageDto chat(@DestinationVariable String roomId, ChatMessageDto message) {
        System.out.println(message);
        return new ChatMessageDto(message.getMessage(), message.getUser());
    }
}
