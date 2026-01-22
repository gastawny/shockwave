package com.gastawny.shockwave.controllers;

import com.gastawny.shockwave.dto.AccountCredentialsDTO;
import com.gastawny.shockwave.dto.TokenDTO;
import com.gastawny.shockwave.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication Endpoint")
@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    private AuthService authServices;

    public AuthController(AuthService authServices) {
        this.authServices = authServices;
    }

    @Operation(summary = "Authenticates a user and returns a token")
    @PostMapping(value = "/signin")
    public ResponseEntity<?> signIn(@RequestBody AccountCredentialsDTO data) {
        if (checkIfParamsNotNull(data))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");

        var token = authServices.signIn(data);

        if (token == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");

        return token;
    }

    @Operation(summary = "Refresh token for authenticated user and returns a new token")
    @PutMapping(value = "/refresh/{id}")
    public ResponseEntity<?> refreshToken(
            @PathVariable("id") Long id,
            @RequestHeader("Authorization") String refreshToken
    ) {
        if (checkIfParamsNotNull(id, refreshToken))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");

        var token = authServices.refreshToken(id, refreshToken);

        if (token == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");

        return token;
    }

    @Operation(summary = "Sign up a new user")
    @PostMapping(path = "/signup")
    public ResponseEntity<TokenDTO> signUp(@Valid @RequestBody AccountCredentialsDTO data) {
        return authServices.signUp(data);
    }

    private static boolean checkIfParamsNotNull(Long id, String refreshToken) {
        return refreshToken == null || refreshToken.isBlank() || id == null;
    }

    private boolean checkIfParamsNotNull(AccountCredentialsDTO data) {
        return data == null || data.getEmail() == null || data.getEmail().isBlank()
                || data.getPassword() == null || data.getPassword().isBlank();
    }
}
