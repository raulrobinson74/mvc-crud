package com.rasysbox.ws.dto.user;

import com.rasysbox.ws.dto.address.AddressDto;
import com.rasysbox.ws.dto.company.CompanyDto;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;
    private AddressDto address;
    private CompanyDto company;
}
