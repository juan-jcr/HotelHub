package com.jacr.services.room;

import com.jacr.presentation.dto.Response;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface RoomService {
    Response addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice, String description);
    Response getAllRooms();
    List<String> getAllRoomTypes();
    Response updateRoom(Long id, String description, String roomType, BigDecimal roomPrice, MultipartFile photo);
    Response deleteRoom(Long id);
    Response getRoomById(Long id);
    Response getAllAvailableRooms();
    Response getAvailableRoomsByDataAndType(LocalDate checkInDate, LocalDate checkOutDate, String roomType);

}
