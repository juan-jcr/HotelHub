package com.jacr.presentation.controller;

import com.jacr.presentation.dto.Response;
import com.jacr.services.room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> addNewRoom(
            @RequestParam(value = "photo", required = false) MultipartFile photo,
            @RequestParam(value = "roomType", required = false) String roomType,
            @RequestParam(value = "roomPrice", required = false) BigDecimal roomPrice,
            @RequestParam(value = "roomDescription", required = false) String roomDescription
    ) {

        if (photo == null || photo.isEmpty() || roomType == null || roomType.isBlank() || roomPrice == null) {
            Response response = new Response();
            response.setMessage("Please Provide values for all fields(photo, roomType, roomPrice)");
        }
        Response response = roomService.addNewRoom(photo, roomType, roomPrice, roomDescription);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Response> getAllRooms() {
        return new ResponseEntity<>(roomService.getAllRooms(), HttpStatus.OK);

    }

    @GetMapping("/types")
    public List<String> getRoomTypes() {
        return roomService.getAllRoomTypes();
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateRoom(
            @PathVariable Long id,
            @RequestParam(value = "photo", required = false) MultipartFile photo,
            @RequestParam(value = "roomType", required = false) String roomType,
            @RequestParam(value = "roomPrice", required = false) BigDecimal roomPrice,
            @RequestParam(value = "roomDescription", required = false) String roomDescription) {

        Response response = roomService.updateRoom(id, roomDescription, roomType, roomPrice, photo);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteRoom(@PathVariable Long id) {
        return new ResponseEntity<>(roomService.deleteRoom(id), HttpStatus.OK);
    }

}
