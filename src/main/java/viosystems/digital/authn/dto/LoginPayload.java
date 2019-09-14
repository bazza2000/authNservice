package viosystems.digital.authn.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginPayload {
    private String Username;
    private String Password;
    private String JourneyId;
}
