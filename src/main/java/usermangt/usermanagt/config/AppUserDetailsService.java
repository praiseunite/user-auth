package usermangt.usermanagt.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import usermangt.usermanagt.dto.response.CustomUserDetails;
import usermangt.usermanagt.model.EntityUser;
import usermangt.usermanagt.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch EntityUser from database
        EntityUser userEntity = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Return a new instance of CustomUserDetails with userEntity
        return new CustomUserDetails(userEntity);
    }
}
