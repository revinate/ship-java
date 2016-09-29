package com.revinate.ship.gueststay;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StayLength {

    public enum StayLengthUnits {
        DAY
    }

    @JsonProperty(required = true)
    @NotNull
    private Integer stayLength;

    @Deprecated
    private StayLengthUnits stayLengthUnits;

    @Builder
    @JsonIgnore
    public StayLength(Integer stayLength) {
        this.stayLength = stayLength;
    }
}
