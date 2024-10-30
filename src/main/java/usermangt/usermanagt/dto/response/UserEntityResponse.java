package usermangt.usermanagt.dto.response;

import lombok.*;
import usermangt.usermanagt.model.EntityUser;

/**
 * DTO for {@link EntityUser}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntityResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String profilePicture;
    private String phoneNumber;
    private String gender;
}