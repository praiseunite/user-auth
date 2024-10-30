package usermangt.usermanagt.dto.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtAuthResponse {
    Long id;
    String firstName;
    String lastName;
    String profilePicture;
    String email;
    String phoneNumber;
    String subscription;
    String accessToken;
    String refreshToken;
    String tokenType = "Bearer";
}
