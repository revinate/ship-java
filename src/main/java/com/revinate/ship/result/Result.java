package com.revinate.ship.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {

    public enum Status {
        NEW, SUCCESS, WARNING, FAILED, MISSING, DELETE;
    }

    private Status status;

    private String message;

    private String sourceField;

    private String sourceValue;

    private String field;
}
