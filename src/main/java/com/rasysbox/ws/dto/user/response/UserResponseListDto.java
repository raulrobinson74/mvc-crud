package com.rasysbox.ws.dto.user.response;

import com.rasysbox.ws.dto.user.UserDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseListDto {
    private int total;
    private String status;
    private String message;
    private List<UserDto> users;
}
