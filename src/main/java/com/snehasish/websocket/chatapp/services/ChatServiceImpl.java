package com.snehasish.websocket.chatapp.services;

import com.snehasish.websocket.chatapp.entities.Message;
import com.snehasish.websocket.chatapp.entities.Room;
import com.snehasish.websocket.chatapp.exceptions.NotFoundException;
import com.snehasish.websocket.chatapp.payload.Request;
import com.snehasish.websocket.chatapp.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{

    private final RoomRepository roomRepository;

    @Override
    public Message sendMessage(String roomId, Request request) {
        Room room = roomRepository.findByRoomId(request.getRoomId()).orElseThrow(() -> new NotFoundException("Room not found."));

        Message message = Message.builder()
                .content(request.getContent())
                .sender(request.getSender())
                .timestamp(LocalDateTime.now())
                .build();

        room.getMessages().add(message);
        roomRepository.save(room);

        return message;
    }
}
