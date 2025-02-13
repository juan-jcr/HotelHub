package com.jacr.services.user;

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
}
