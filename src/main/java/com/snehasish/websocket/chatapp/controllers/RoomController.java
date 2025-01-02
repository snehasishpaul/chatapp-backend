package com.snehasish.websocket.chatapp.controllers;

import com.snehasish.websocket.chatapp.entities.Room;
import com.snehasish.websocket.chatapp.payload.Constants;
import com.snehasish.websocket.chatapp.payload.Request;
import com.snehasish.websocket.chatapp.payload.Response;
import com.snehasish.websocket.chatapp.repositories.RoomRepository;
import com.snehasish.websocket.chatapp.services.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
@CrossOrigin(Constants.ALLOWED_ORIGIN)
public class RoomController {

    private final RoomService roomService;

    //create room
    @PostMapping
    public ResponseEntity<Response<?>> createRoom(@Valid @RequestBody Request request) {
        Response<?> res = roomService.createRoom(request);
        return ResponseEntity.status(res.getHttpStatus()).body(res);
    }

    //get room
    @GetMapping("/{roomId}")
    public ResponseEntity<Response<?>> joinRoom(@Valid @PathVariable("roomId") Request request) {
        Response<?> res = roomService.joinRoom(request.getRoomId());
        return ResponseEntity.status(res.getHttpStatus()).body(res);
    }


    //get messages for room
    @GetMapping("/{roomId}/messages")
    public ResponseEntity<Response<?>> getMessagesForRoom(@Valid @PathVariable("roomId") Request request,
                                                          @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                          @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
        Response<?> res = roomService.getMessagesForRoom(request.getRoomId(), page, size);
        return ResponseEntity.status(res.getHttpStatus()).body(res);
    }
}
