package viosystems.digital.authn;



import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import viosystems.digital.authn.domain.UserProfile;
import viosystems.digital.authn.dto.LoginPayload;
import viosystems.digital.authn.dto.UserProfileDto;
import viosystems.digital.authn.persistence.UserRepository;
import viosystems.digital.telemetry.EndpointIdentifier;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


@Slf4j
@RestController
public class AuthnController {

    @Autowired
    UserRepository userRepository;

    @EndpointIdentifier(id="JIRA-104")
    @PostMapping(value = "/login")
    public @ResponseBody
    UserProfileDto getPayload(HttpServletRequest servletRequest, @RequestBody LoginPayload loginPayload) {
        Optional<UserProfile> optionalUserProfile = userRepository.findByUsername(loginPayload.getUsername());
        if(optionalUserProfile.isPresent()) {
            UserProfile userProfile = optionalUserProfile.get();
            log.info("returning profile data for " + userProfile.getUsername());
            return assembleDto(loginPayload, userProfile);
        }
        else {
            log.info("user not found " + loginPayload.getUsername());
            return UserProfileDto.builder().firstName("Unknown").surname("Unknown").build();
        }
    }

    private UserProfileDto assembleDto(@RequestBody LoginPayload loginPayload, UserProfile userProfile) {
        return UserProfileDto.builder()
                .firstName(userProfile.getFirstName())
                .surname(userProfile.getSurname())
                .email(userProfile.getEmail())
                .username(userProfile.getUsername()).build();
    }

}
