package com.snehasish.websocket.chatapp.services;

import com.snehasish.websocket.chatapp.entities.Message;
import com.snehasish.websocket.chatapp.entities.Room;
import com.snehasish.websocket.chatapp.payload.Request;
import com.snehasish.websocket.chatapp.payload.Response;
import com.snehasish.websocket.chatapp.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public Response<?> createRoom(String roomId) {
        Optional<Room> roomOpt = roomRepository.findByRoomId(roomId);
        if (roomOpt.isPresent()) {
            return Response.builder()
                    .message("Room Already Exists.")
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .build();
//            Response<?> response = new Response<>();
//            response.setMessage("Room Already Exists.");
//            response.setHttpStatus(HttpStatus.BAD_REQUEST);
//            return response;
        }

//        Room room = Room.builder().roomId(request.getRoomId()).build();
        Room room = new Room();
        room.setRoomId(roomId);
        Room savedRoom = roomRepository.save(room);
        return Response.builder()
                .content(savedRoom)
                .message("Room created successfully.")
                .httpStatus(HttpStatus.CREATED)
                .build();
//        Response<Room> response = new Response<>();
//        response.setContent(savedRoom);
//        response.setMessage("Room created successfully.");
//        response.setHttpStatus(HttpStatus.CREATED);
//        return response;
    }


    @Override
    public Response<?> joinRoom(String roomId) {
        Optional<Room> roomOpt = roomRepository.findByRoomId(roomId);
        if (roomOpt.isEmpty()) {
            return Response.builder()
                    .message("Room not found.")
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .build();
//            Response<Room> response = new Response<>();
//            response.setMessage("Room not found.");
//            response.setHttpStatus(HttpStatus.BAD_REQUEST);
//            return response;
        }
        return Response.builder()
                .content(roomOpt.get())
                .httpStatus(HttpStatus.OK)
                .build();
//        Response<Room> response = new Response<>();
//        response.setContent(roomOpt.get());
//        response.setHttpStatus(HttpStatus.OK);
//        return response;
    }

    @Override
    public Response<?> getMessagesForRoom(String roomId, int page, int size) {
        Optional<Room> roomOpt = roomRepository.findByRoomId(roomId);
        if (roomOpt.isEmpty()) {
            return Response.builder()
                    .message("Room not found.")
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .build();
//            Response<Room> response = new Response<>();
//            response.setMessage("Room not found.");
//            response.setHttpStatus(HttpStatus.BAD_REQUEST);
//            return response;
        }

        Room room = roomOpt.get();
        List<Message> messages = room.getMessages();

        int start = Math.max(0, messages.size() - (page + 1) * size);
        int end = Math.min(messages.size(), start + size);
        List<Message> paginatedMessages = messages.subList(start, end);

        return Response.builder()
                .content(paginatedMessages)
                .httpStatus(HttpStatus.OK)
                .build();
//        Response<List<Message>> response = new Response<>();
//        response.setContent(paginatedMessages);
//        response.setHttpStatus(HttpStatus.OK);
//        return response;

    }

}
