package com.jacr.services.user;

import com.jacr.exception.ResourceNotFoundException;
import com.jacr.persistence.entities.UserEntity;
import com.jacr.persistence.repositories.UserRepository;
import com.jacr.presentation.dto.Response;
import com.jacr.presentation.dto.UserDTO;
import com.jacr.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;



    @Override
    public Response getAllUsers() {
        Response response = new Response();

        List<UserEntity> userEntities = (List<UserEntity>) userRepository.findAll();
        List<UserDTO> userDTOList = Utils.mapUserListEntityToUserListDTO(userEntities);

        response.setUserList(userDTOList);
        response.setMessage("successful");

       return response;
    }

    @Override
    public Response getUserById(Long id) {
        Response response = new Response();

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not foud"));

        UserDTO userDTO = Utils.mapUserEntityToUserDTO(user);
        response.setMessage("successful");
        response.setUser(userDTO);
        return response;


    }

    @Override
    public Response getMyInfo(String email) {
        Response response = new Response();

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not foud"));
        UserDTO userDTO = Utils.mapUserEntityToUserDTO(user);

        response.setMessage("successful");
        response.setUser(userDTO);
        return response;
    }

    @Override
    public Response deleteUser(Long id) {
        Response response = new Response();
        userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not foud"));
        userRepository.deleteById(id);
        response.setMessage("successful");
        return response;
    }

    @Override
    public Response getUserBookingHistory(Long id) {
        Response response = new Response();

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        UserDTO userDTO = Utils.mapUserEntityToUserDTOPlusUserBookingsAndRoom(user);
        response.setMessage("successful");
        response.setUser(userDTO);
        return response;
    }
}
