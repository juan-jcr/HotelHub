package com.jacr.services.auth;

import com.jacr.exception.AlreadyExistsException;
import com.jacr.exception.ResourceNotFoundException;
import com.jacr.persistence.entities.UserEntity;
import com.jacr.persistence.repositories.UserRepository;
import com.jacr.presentation.dto.LoginRequest;
import com.jacr.presentation.dto.Response;
import com.jacr.presentation.dto.UserDTO;
import com.jacr.utils.JWTUtils;
import com.jacr.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Response registerUser(UserEntity user) {
        Response response = new Response();

        try {
            if (user.getRole() == null || user.getRole().isBlank()) {
                user.setRole("USER");
            }
            if (userRepository.existsByEmail(user.getEmail())) {
                throw new AlreadyExistsException(user.getEmail() + " " + "Already Exists");
            }

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            UserEntity savedUser = userRepository.save(user);
            UserDTO userDTO = Utils.mapUserEntityToUserDTO(savedUser);

            response.setUser(userDTO);
            response.setMessage("successful");

        } catch (AlreadyExistsException e) {
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public Response login(LoginRequest loginRequest) {
        Response response = new Response();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
            var token = jwtUtils.generateToken(user);

            response.setToken(token);
            response.setExpirationTime("7 days");
            response.setRole(user.getRole());
            response.setMessage("successful");

        } catch (AlreadyExistsException e) {
            response.setMessage(e.getMessage());

        }
        return response;
    }
}
