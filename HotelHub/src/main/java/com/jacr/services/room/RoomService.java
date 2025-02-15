package com.jacr.services.room;

import com.jacr.presentation.dto.Response;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public interface RoomService {
    Response addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice, String description);
    Response getAllRooms();
    Response updateRoom(Long id, String description, String roomType, BigDecimal roomPrice, MultipartFile photo);

}
