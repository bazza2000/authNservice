package viosystems.digital.demo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserProfile {
    private String firstName;
    private String surname;
    private String email;
    private String username;
}
