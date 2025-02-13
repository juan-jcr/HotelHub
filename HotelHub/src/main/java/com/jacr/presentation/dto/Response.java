package com.jacr.presentation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private String message;

    private String token;
    private String role;
    private String expirationTime;

    private UserDTO user;
    private List<UserDTO> userList;

    public Response(){}

    public Response(String message, String token, String role, String expirationTime, UserDTO user, List<UserDTO> userList) {
        this.message = message;
        this.token = token;
        this.role = role;
        this.expirationTime = expirationTime;
        this.user = user;
        this.userList = userList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<UserDTO> getUserList() {
        return userList;
    }

    public void setUserList(List<UserDTO> userList) {
        this.userList = userList;
    }
}
