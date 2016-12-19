package com.revinate.ship.result;

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
public class Summary {

    @JsonProperty(required = true)
    @NotNull
    private Long successCount;

    @JsonProperty(required = true)
    @NotNull
    private Long errorCount;

    @JsonProperty(required = true)
    @NotNull
    private Long totalCount;

    @JsonIgnore
    public Summary(Long successCount, Long errorCount, Long totalCount) {
        this.successCount = successCount;
        this.errorCount = errorCount;
        this.totalCount = totalCount;
    }
}
