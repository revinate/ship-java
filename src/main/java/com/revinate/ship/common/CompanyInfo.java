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
public class CompanyInfo {

    @JsonProperty(required = true)
    @NotNull
    private String name;

    @JsonIgnore
    public CompanyInfo(String name) {
        this.name = name;
    }
}
