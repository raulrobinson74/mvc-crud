package com.rasysbox.ws.dto.company;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    private Long id;
    private String name;
    private String catchPhrase;
    private String bs;
}
