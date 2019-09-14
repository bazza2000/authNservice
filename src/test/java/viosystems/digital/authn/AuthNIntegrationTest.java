package viosystems.digital.authn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import viosystems.digital.authn.domain.UserProfile;
import viosystems.digital.authn.dto.LoginPayload;
import viosystems.digital.authn.dto.UserProfileDto;
import viosystems.digital.authn.persistence.UserRepository;
import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AuthnApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AuthNIntegrationTest {



    @LocalServerPort
    int randomServerPort;

    @MockBean
    private UserRepository repository;

    @Autowired
    private TestRestTemplate restTemplate;


    public static final String EMAIL = "xxx@xy.com";
    public static final String USERNAME = "jimbo";

    @Test
    public void shouldReturnAProfileFromLogin() throws URISyntaxException {
        final  URI uri  = new URI("http://localhost:"+ randomServerPort +"/authn/login");
        LoginPayload loginPayload = LoginPayload.builder().username(USERNAME).build();


        HttpEntity<LoginPayload> entity = new HttpEntity<>(loginPayload, createHeaders());
        ResponseEntity<UserProfileDto> result = this.restTemplate.exchange(uri, HttpMethod.POST, entity,
                UserProfileDto.class);

        UserProfileDto user = result.getBody();
        assertEquals("Email failed", EMAIL, user.getEmail());
        assertEquals("Username failed", USERNAME, user.getUsername());

    }

    @Before
    public void createMockResponse()
    {
        when(repository.findByUsername(any())).thenReturn(
                Optional.of(
                        UserProfile.builder()

                                .firstName("Jim")
                                .email(EMAIL)
                                .surname("lack")
                                .username(USERNAME)
                                .build()));
    }

    private HttpHeaders createHeaders() throws URISyntaxException {
        final HttpHeaders httpHeaders = new HttpHeaders();
        return httpHeaders;
    }

}
