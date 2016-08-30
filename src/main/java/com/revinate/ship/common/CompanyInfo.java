package com.revinate.ship.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CompanyInfo {

    @JsonProperty(required = true)
    private String name;
}
