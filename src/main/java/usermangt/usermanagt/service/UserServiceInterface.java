package usermangt.usermanagt.service;



import org.springframework.http.ResponseEntity;
import usermangt.usermanagt.dto.request.LoginRequest;
import usermangt.usermanagt.dto.request.UserRequestDto;
import usermangt.usermanagt.dto.response.ApiResponse;
import usermangt.usermanagt.dto.response.JwtAuthResponse;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserServiceInterface {
    ResponseEntity<ApiResponse<UserRequestDto>> registerUser (UserRequestDto userRequestDto);
    void logout();
    ResponseEntity<ApiResponse<JwtAuthResponse>> login(LoginRequest loginRequest) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException;
}
