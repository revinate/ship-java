package com.revinate.ship.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDefinedField {

    @JsonProperty(required = true)
    private String name;
    @JsonProperty(required = true)
    private String value;
}
