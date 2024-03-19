package com.rasysbox.ws.dto.geo;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeoDto {
    private Long id;
    private String lat;
    private String lng;
}
