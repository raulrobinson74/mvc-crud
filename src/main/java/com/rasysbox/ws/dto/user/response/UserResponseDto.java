package com.rasysbox.ws.dto.user.response;

import com.rasysbox.ws.dto.user.UserDto;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String status;
    private String message;
    private UserDto user;
}
