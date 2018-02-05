package com.revinate.ship.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileListResult extends Result {

    @Valid
    private Summary summary;

    @Valid
    private List<ProfileResult> profiles;

    public void addProfile(ProfileResult profile) {
        if (Objects.isNull(this.profiles)) {
            this.profiles = new ArrayList<>();
        }
        this.profiles.add(profile);
    }

    public void addProfiles(List<ProfileResult> profiles) {
        if (Objects.isNull(this.profiles)) {
            this.profiles = new ArrayList<>();
        }
        this.profiles.addAll(profiles);
    }

    @Override
    public String toString() {
        return summary.toString();
    }
}
