package com.rasysbox.ws.dto.company.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequestDto {
    private String name;
    private String catchPhrase;
    private String bs;
}
