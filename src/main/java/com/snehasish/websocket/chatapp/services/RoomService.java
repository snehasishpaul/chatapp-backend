package com.snehasish.websocket.chatapp.services;

import com.snehasish.websocket.chatapp.entities.Room;
import com.snehasish.websocket.chatapp.payload.Request;
import com.snehasish.websocket.chatapp.payload.Response;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public interface RoomService {
    

    Response<?> joinRoom(@NotBlank String roomId);

    Response<?> createRoom(@Valid Request request);

    Response<?> getMessagesForRoom(@NotBlank String roomId, int page, int size);
}
