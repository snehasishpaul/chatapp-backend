package com.snehasish.websocket.chatapp.controllers;

import com.snehasish.websocket.chatapp.entities.Message;
import com.snehasish.websocket.chatapp.entities.Room;
import com.snehasish.websocket.chatapp.exceptions.NotFoundException;
import com.snehasish.websocket.chatapp.payload.Constants;
import com.snehasish.websocket.chatapp.payload.Request;
import com.snehasish.websocket.chatapp.repositories.RoomRepository;
import com.snehasish.websocket.chatapp.services.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@CrossOrigin(Constants.ALLOWED_ORIGIN)
public class ChatController {

    private final ChatService chatService;

    //send and receive messages to subscribed
    @MessageMapping("/sendMessage/{roomId}")    // /app/sendMessage/roomId
    @SendTo("/topic/room/{roomId}")             // subscribe
    public Message sendMessage(@Valid @DestinationVariable String roomId, @Valid @RequestBody Request request) {
        return chatService.sendMessage(roomId, request);
    }
}
