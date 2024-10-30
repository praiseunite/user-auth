package usermangt.usermanagt.service;

import com.ibm.java.diagnostics.utils.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import usermangt.usermanagt.config.JWTGenerator;
import usermangt.usermanagt.dto.request.LoginRequest;
import usermangt.usermanagt.dto.request.UserRequestDto;
import usermangt.usermanagt.dto.response.ApiResponse;
import usermangt.usermanagt.dto.response.CustomUserDetails;
import usermangt.usermanagt.dto.response.JwtAuthResponse;
import usermangt.usermanagt.model.EntityUser;
import usermangt.usermanagt.repository.UserRepository;

import static com.ibm.java.diagnostics.utils.Context.logger;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTGenerator jwtTokenUtil;

    @Override
    public ResponseEntity<ApiResponse<UserRequestDto>> registerUser(UserRequestDto userRequestDto) {
        if (userRepository.findByEmail(userRequestDto.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("User already exists"));
        }

        EntityUser user = new EntityUser();
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));

        userRepository.save(user);
        return ResponseEntity.ok(new ApiResponse<>("User registered successfully", userRequestDto));
    }

    @Override
    public void logout() {
        SecurityContextHolder.clearContext();
    }
    @Override
    public ResponseEntity<ApiResponse<JwtAuthResponse>> login(LoginRequest loginRequest) {
        try {
            // Authenticate using email and password
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            // Set the authentication in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);
            EntityUser user = ((CustomUserDetails) authentication.getPrincipal()).getEntityUser();

            // Generate JWT token
            String jwt = jwtTokenUtil.generateToken(authentication, user.getId());

            // Create the response object
            JwtAuthResponse response = JwtAuthResponse.builder()
                    .id(user.getId())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .accessToken(jwt)
                    .build();

            // Log successful login
            logger.info(String.format("User %s logged in successfully.", user.getEmail()));

            return ResponseEntity.ok(new ApiResponse<>("Login successful", response));
        } catch (AuthenticationException e) {
            // Log failed login attempt
            logger.warning(String.format("Failed login attempt for user %s: %s", loginRequest.getEmail(), e.getMessage()));

            // Return error response
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>("Login failed: Invalid email or password", null));
        } catch (Exception e) {
            // Log unexpected errors
            logger.severe(String.format("An unexpected error occurred during login: %s", e.getMessage()));

            // Return generic error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("An unexpected error occurred. Please try again.", null));
        }
    }

}
