package com.rasysbox.ws.services.impl;

import com.rasysbox.ws.dto.user.request.UserRequestDto;
import com.rasysbox.ws.dto.user.response.UserResponseDto;
import com.rasysbox.ws.dto.user.response.UserResponseListDto;
import com.rasysbox.ws.entity.User;
import com.rasysbox.ws.repository.UserRepository;
import com.rasysbox.ws.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.rasysbox.ws.mappers.UserMapper.*;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<UserResponseListDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (!users.isEmpty()) {
            LOGGER.info("users found: {}", users.size());
            return new ResponseEntity<>(UserResponseListDto.builder()
                    .status("200 OK")
                    .message("users found: " + users.size())
                    .total(users.size())
                    .users(userDtoListFromUsers(users))
                    .build(), HttpStatus.OK);
        } else {
            LOGGER.warn("users not found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<UserResponseDto> getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            LOGGER.info("user found with userId: {}", user.get().getId());
            return new ResponseEntity<>(UserResponseDtoFromOptionalUser(user, "200 OK", "user found with userId: " + user.get().getId()), HttpStatus.OK);
        } else {
            LOGGER.warn("user not found with userId: {}", userId);
            return new ResponseEntity<>(UserResponseDto.builder()
                    .status("404 Not Found")
                    .message("user not found with userId: " + userId)
                    .build(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<UserResponseDto> createUser(UserRequestDto user) {
        User userFindByUsername = userRepository.findUserByUsername(user.getUsername());
        User userFindByEmail = userRepository.findUserByEmail(user.getEmail());

        if (userFindByUsername != null) {
            return new ResponseEntity<>(UserResponseDto.builder()
                    .status("409 Conflict")
                    .message("username already exist")
                    .user(null)
                    .build(), HttpStatus.CONFLICT);
        } else if (userFindByEmail != null) {
            return new ResponseEntity<>(UserResponseDto.builder()
                    .status("409 Conflict")
                    .message("email already exist")
                    .user(null)
                    .build(), HttpStatus.CONFLICT);
        }

        User userSave = userRepository.save(userRequestDto(user));

        if (userSave.getId() == null) {
            return new ResponseEntity<>(UserResponseDto.builder()
                    .status("400 Bad Request")
                    .message("user could not be created")
                    .user(null)
                    .build(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(UserResponseDto.builder()
                .status("201 Created")
                .message("user created assigned userId: " + userSave.getId())
                .user(userDtoFromUser(userSave))
                .build(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserResponseDto> updateUser(Long userId, UserRequestDto updatedUser) {
        Optional<User> userFindById = userRepository.findById(userId);

        if (userFindById.isEmpty()) {
            LOGGER.info("user not found with userId: {}", userId);
            return createNotFoundResponse("user not found with userId: " + userId);
        }

        User user = userFindById.get();

        if (!Objects.equals(user.getUsername(), updatedUser.getUsername())) {
            LOGGER.warn("user not found with username: {}", updatedUser.getUsername());
            return createNotFoundResponse("user not found with username: " + updatedUser.getUsername());
        }

        if (!Objects.equals(user.getEmail(), updatedUser.getEmail())) {
            LOGGER.warn("user not found with email: {}", updatedUser.getEmail());
            return createNotFoundResponse("user not found with email: " + updatedUser.getEmail());
        }

        User userUpdate = userRepository.save(userUpdateRequestDto(updatedUser,
                userId,
                userFindById.get().getAddress().getId(),
                userFindById.get().getAddress().getGeo().getId(),
                userFindById.get().getCompany().getId()));

        if (userUpdate.getId() == null) {
            LOGGER.error("Bad Request");
            return new ResponseEntity<>(UserResponseDto.builder()
                    .status("400 Bad Request")
                    .message("user could not be updated")
                    .build(), HttpStatus.BAD_REQUEST);
        }

        LOGGER.info("user updated with id: {}", userUpdate.getId());
        return new ResponseEntity<>(UserResponseDto.builder()
                .status("200 OK")
                .message("user updated with id: " + userUpdate.getId())
                .user(userDtoFromUser(userUpdate))
                .build(), HttpStatus.OK);
    }

    private ResponseEntity<UserResponseDto> createNotFoundResponse(String message) {
        LOGGER.warn(message);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(UserResponseDto.builder()
                        .status("404 Not Found")
                        .message(message)
                        .build());
    }

    @Override
    public ResponseEntity<UserResponseDto> deleteUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(userId);
            LOGGER.info("user deleted with id: {}", userId);
            return new ResponseEntity<>(UserResponseDto.builder()
                    .status("200 OK")
                    .message("user deleted with id: " + optionalUser.get().getId())
                    .user(userDtoFromUser(optionalUser.get()))
                    .build(), HttpStatus.OK);
        }
        LOGGER.info("user not found with userId: {}", userId);
        return createNotFoundResponse("user not found with userId: " + userId);
    }

}
