package usermangt.usermanagt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import usermangt.usermanagt.model.EntityUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<EntityUser, Long> {
    Optional<EntityUser> findByEmail(String email);
}
