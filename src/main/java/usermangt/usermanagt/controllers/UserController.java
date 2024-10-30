package usermangt.usermanagt.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usermangt.usermanagt.dto.request.LoginRequest;
import usermangt.usermanagt.dto.request.UserRequestDto;
import usermangt.usermanagt.dto.response.ApiResponse;
import usermangt.usermanagt.dto.response.JwtAuthResponse;
import usermangt.usermanagt.service.UserServiceInterface;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceInterface userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserRequestDto>> registerUser(@RequestBody UserRequestDto userRequestDto) {
        return userService.registerUser(userRequestDto);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<JwtAuthResponse>> loginUser(@RequestBody LoginRequest loginRequest) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return userService.login(loginRequest);
    }

    @GetMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logoutUser() {
        userService.logout();
        return ResponseEntity.ok(new ApiResponse<>("Logout successful"));
    }
}
