package com.snehasish.websocket.chatapp.services;

import com.snehasish.websocket.chatapp.entities.Message;
import com.snehasish.websocket.chatapp.payload.Request;
import jakarta.validation.Valid;

public interface ChatService {

    Message sendMessage(@Valid String roomId, @Valid Request request);
}
