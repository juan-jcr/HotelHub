package com.jacr.services.room;


import com.jacr.exception.ResourceNotFoundException;
import com.jacr.persistence.entities.Room;
import com.jacr.persistence.repositories.RoomRepository;
import com.jacr.presentation.dto.Response;
import com.jacr.presentation.dto.RoomDTO;
import com.jacr.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

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

    @Override
    public Response getAllRooms() {
        Response response = new Response();

        List<Room> roomList = roomRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        List<RoomDTO> roomDTOList = Utils.mapRoomListEntityToRoomListDTO(roomList);

        response.setMessage("successful");
        response.setRoomList(roomDTOList);

        return response;
    }

    @Override
    public List<String> getAllRoomTypes() {
        return roomRepository.findDistinctRoomTypes();
    }

    @Override
    public Response updateRoom(Long id, String description, String roomType, BigDecimal roomPrice, MultipartFile photo) {
        Response response = new Response();

        try{
            String imageUrl = null;
            if (photo != null && !photo.isEmpty()){
                imageUrl = uploadService.saveUpload(photo);
            }
            Room room = roomRepository
                    .findById(id).orElseThrow(() -> new ResourceNotFoundException("Room not found"));

            room.setRoomType(roomType);
            room.setRoomPrice(roomPrice);
            room.setRoomDescription(description);
            room.setRoomPhotoUrl(imageUrl);

            Room updatedRoom = roomRepository.save(room);
            RoomDTO roomDTO = Utils.mapRoomEntityToRoomDTO(updatedRoom);

            response.setMessage("successful");
            response.setRoom(roomDTO);

        } catch (Exception e) {
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public Response deleteRoom(Long id) {
        Response response = new Response();

        roomRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Room Not Found"));
        roomRepository.deleteById(id);
        response.setMessage("successful");

        return response;
    }

    @Override
    public Response getRoomById(Long id) {
        Response response = new Response();

        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room Not Found"));

        RoomDTO roomDTO = Utils.mapRoomEntityToRoomDTOPlusBookings(room);
        response.setMessage("successful");
        response.setRoom(roomDTO);

        return response;
    }
}
