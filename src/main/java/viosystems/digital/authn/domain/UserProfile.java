package viosystems.digital.authn.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER_PROFILE")
@Entity
public class UserProfile {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",
            strategy = "org.hibernate.id.UUIDGenerator")

    private String userId;
    private String username;
    private String email;
    private String firstName;
    private String surname;
}
