package viosystems.digital.authn.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserProfileDto {
    private String firstName;
    private String surName;
    private String email;
    private String username;
}
