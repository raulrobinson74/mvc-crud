package com.rasysbox.ws.controller;

import com.rasysbox.ws.dto.user.request.UserRequestDto;
import com.rasysbox.ws.dto.user.response.UserResponseDto;
import com.rasysbox.ws.dto.user.response.UserResponseListDto;
import com.rasysbox.ws.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.rasysbox.ws.mappers.UserMapper.*;
import static com.rasysbox.ws.utils.Shield.blindLong;

@RestController
@RequestMapping("${controller.properties.base-path}" + "/users")
@Tag(name = "1. Users", description = "Users Services.")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "List all Users", description = "List all Users.")
    public ResponseEntity<UserResponseListDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Find User", description = "Find user by user Id.")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long userId) {
        return userService.getUserById(blindLong(userId));
    }

    @PostMapping
    @Operation(summary = "Create User", description = "Create a user.")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto user) {
        return userService.createUser(userRequestDtoFromUserRequestDto(user));
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Update User", description = "Update user by user Id.")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long userId, @RequestBody UserRequestDto updatedUser) {
        return userService.updateUser(userId, updatedUser);
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete User", description = "Delete user by user Id.")
    public ResponseEntity<UserResponseDto> deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(blindLong(userId));
    }

}
