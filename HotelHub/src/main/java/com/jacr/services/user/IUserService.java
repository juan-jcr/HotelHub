package com.jacr.services.user;

import com.jacr.presentation.dto.Response;

public interface IUserService {
    Response getAllUsers();
    Response getUserById(Long id);
    Response getMyInfo(String email);
    Response deleteUser(Long id);

}
