package com.rasysbox.ws.dto.user.request;

import com.rasysbox.ws.dto.address.request.AddressRequestDto;
import com.rasysbox.ws.dto.company.request.CompanyRequestDto;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;
    private AddressRequestDto address;
    private CompanyRequestDto company;
}
