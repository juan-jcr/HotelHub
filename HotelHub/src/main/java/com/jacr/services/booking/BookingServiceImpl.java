package com.jacr.services.booking;

import com.jacr.exception.AlreadyExistsException;
import com.jacr.exception.ResourceNotFoundException;
import com.jacr.persistence.entities.Booking;
import com.jacr.persistence.entities.Room;
import com.jacr.persistence.entities.UserEntity;
import com.jacr.persistence.repositories.BookingRepository;
import com.jacr.persistence.repositories.RoomRepository;
import com.jacr.persistence.repositories.UserRepository;
import com.jacr.presentation.dto.BookingDTO;
import com.jacr.presentation.dto.Response;
import com.jacr.services.room.RoomService;
import com.jacr.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public Response saveBooking(Long roomId, Long userId, Booking bookingRequest) {
        Response response = new Response();

        if (bookingRequest.getCheckOutDate().isBefore(bookingRequest.getCheckInDate())) {
            throw new IllegalArgumentException("Check in date must come after check out date");
        }

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room Not Found"));
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        List<Booking> existingBookings = room.getBookings();

        if (!roomIsAvailable(bookingRequest, existingBookings)) {
            throw new ResourceNotFoundException("Room not Available for selected date range");
        }
        bookingRequest.setRoom(room);
        bookingRequest.setUser(user);
        String bookingConfirmationCode = Utils.generateRandomConfirmationCode(10);
        bookingRequest.setBookingConfirmationCode(bookingConfirmationCode);
        bookingRepository.save(bookingRequest);
        response.setMessage("successful");
        response.setBookingConfirmationCode(bookingConfirmationCode);

        return response;
    }

    @Override
    public Response findBookingByConfirmationCode(String confirmationCode) {
        Response response = new Response();
        Booking booking = bookingRepository.findByBookingConfirmationCode(confirmationCode)
                .orElseThrow(() -> new ResourceNotFoundException("Booking Not Found"));
        BookingDTO bookingDTO = Utils.mapBookingEntityToBookingDTOPlusBookedRooms(booking, true);
        response.setMessage("successful");
        response.setBooking(bookingDTO);
        return response;
    }

    @Override
    public Response getAllBookings() {
        Response response = new Response();
        List<Booking> bookingList = bookingRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        List<BookingDTO> bookingDTOList = Utils.mapBookingListEntityToBookingListDTO(bookingList);
        response.setMessage("successful");
        response.setBookingList(bookingDTOList);
        return response;
    }

    @Override
    public Response cancelBooking(Long bookingId) {
        Response response = new Response();
        bookingRepository.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException("Booking Does Not Exist"));
        bookingRepository.deleteById(bookingId);
        response.setMessage("successful");
        return response;
    }

    private boolean roomIsAvailable(Booking bookingRequest, List<Booking> existingBookings) {

        return existingBookings.stream()
                .noneMatch(existingBooking ->
                        bookingRequest.getCheckInDate().equals(existingBooking.getCheckInDate())
                                || bookingRequest.getCheckOutDate().isBefore(existingBooking.getCheckOutDate())
                                || (bookingRequest.getCheckInDate().isAfter(existingBooking.getCheckInDate())
                                && bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckOutDate()))
                                || (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())

                                && bookingRequest.getCheckOutDate().equals(existingBooking.getCheckOutDate()))
                                || (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())

                                && bookingRequest.getCheckOutDate().isAfter(existingBooking.getCheckOutDate()))

                                || (bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate())
                                && bookingRequest.getCheckOutDate().equals(existingBooking.getCheckInDate()))

                                || (bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate())
                                && bookingRequest.getCheckOutDate().equals(bookingRequest.getCheckInDate()))
                );
    }
}
