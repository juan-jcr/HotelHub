package com.jacr.services.room;


import com.jacr.persistence.entities.Room;
import com.jacr.persistence.repositories.RoomRepository;
import com.jacr.presentation.dto.Response;
import com.jacr.presentation.dto.RoomDTO;
import com.jacr.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UploadService uploadService;

    @Override
    public Response addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice, String description) {
        Response response = new Response();
        try {
            String imageUrl = uploadService.saveUpload(photo);
            Room room = new Room();

            room.setRoomPhotoUrl(imageUrl);
            room.setRoomType(roomType);
            room.setRoomPrice(roomPrice);
            room.setRoomDescription(description);

            Room savedRoom = roomRepository.save(room);
            RoomDTO roomDTO = Utils.mapRoomEntityToRoomDTO(savedRoom);

            response.setRoom(roomDTO);
            response.setMessage("successful");

        } catch (Exception e) {
            response.setMessage("Error saving a room " + e.getMessage());
        }
        return response;
    }
}
