package viosystems.digital.demo;



import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import viosystems.digital.telemetry.EndpointIdentifier;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestController
public class DemoController {
    @EndpointIdentifier(id="JIRA-104")
    @PostMapping(value = "/login")
    public @ResponseBody
    UserProfile getPayload(HttpServletRequest servletRequest, @RequestBody LoginPayload loginPayload) {
            log.info("returning profile data for " + loginPayload.getUsername());
            return UserProfile.builder().firstName("Jim").surname("Lack").build();
    }

}
