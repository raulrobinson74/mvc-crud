package com.rasysbox.ws.dto.address.request;

import com.rasysbox.ws.dto.geo.request.GeoRequestDto;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestDto {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoRequestDto geo;
}
