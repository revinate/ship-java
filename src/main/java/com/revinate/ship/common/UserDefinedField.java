package com.revinate.ship.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDefinedField {

    @JsonProperty(required = true)
    private String name;
    @JsonProperty(required = true)
    private String value;

    @JsonIgnore
    public UserDefinedField(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
