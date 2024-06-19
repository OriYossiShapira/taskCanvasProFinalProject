package com.ori.taskcanvaspro.service;

import com.ori.taskcanvaspro.dto.LoginDTO;
import com.ori.taskcanvaspro.dto.UserCreateDTO;
import com.ori.taskcanvaspro.dto.UserResponseDTO;
import org.springframework.stereotype.Service;


public interface UserService {
    UserResponseDTO register(UserCreateDTO user);
    UserResponseDTO login(LoginDTO login);
    UserResponseDTO update(UserResponseDTO user);

    UserResponseDTO getUser(Long id);
    public UserResponseDTO getByEmail(String email);
}
