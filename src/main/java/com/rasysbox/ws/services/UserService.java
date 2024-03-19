package com.rasysbox.ws.services;

import com.rasysbox.ws.dto.user.request.UserRequestDto;
import com.rasysbox.ws.dto.user.response.UserResponseDto;
import com.rasysbox.ws.dto.user.response.UserResponseListDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<UserResponseListDto> getAllUsers();
    ResponseEntity<UserResponseDto> getUserById(Long userId);
    ResponseEntity<UserResponseDto> createUser(UserRequestDto user);
    ResponseEntity<UserResponseDto> updateUser(Long userId, UserRequestDto updatedUser);
    ResponseEntity<UserResponseDto> deleteUser(Long userId);
}
