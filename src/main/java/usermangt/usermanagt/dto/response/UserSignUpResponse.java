package usermangt.usermanagt.dto.response;

import lombok.*;
import usermangt.usermanagt.model.enums.Gender;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpResponse {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Gender gender;

}
