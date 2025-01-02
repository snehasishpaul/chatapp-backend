package com.snehasish.websocket.chatapp.repositories;

import com.snehasish.websocket.chatapp.entities.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoomRepository extends MongoRepository<Room, String> {
    Optional<Room> findByRoomId(String roomId);
}
