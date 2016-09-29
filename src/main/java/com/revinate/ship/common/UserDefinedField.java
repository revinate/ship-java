package com.revinate.ship.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDefinedField {

    @JsonProperty(required = true)
    @NotNull
    private String name;

    @JsonProperty(required = true)
    @NotNull
    private String value;

    @JsonIgnore
    public UserDefinedField(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
