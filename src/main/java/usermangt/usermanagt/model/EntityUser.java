package usermangt.usermanagt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntityUser extends BaseEntity {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    @Transient
    private String confirmPassword;

    private String phoneNumber;

    private String profilePicture;

    private boolean isVerified = false;
}
