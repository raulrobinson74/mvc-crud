package com.rasysbox.ws.dto.address;

import com.rasysbox.ws.dto.geo.GeoDto;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private Long id;
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoDto geo;
}
