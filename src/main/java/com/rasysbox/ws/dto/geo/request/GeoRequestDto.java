package com.rasysbox.ws.dto.geo.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeoRequestDto {
    private String lat;
    private String lng;
}
