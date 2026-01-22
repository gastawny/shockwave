package com.gastawny.shockwave.services;

import com.gastawny.shockwave.dto.AccountCredentialsDTO;
import com.gastawny.shockwave.dto.TokenDTO;
import com.gastawny.shockwave.models.User;
import com.gastawny.shockwave.repositories.ExplosiveRepository;
import com.gastawny.shockwave.repositories.UserRepository;
import com.gastawny.shockwave.security.jwt.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private JwtTokenProvider tokenProvider;
    private AuthenticationManager authenticationManager;
    private UserRepository repository;
    private ExplosiveRepository explosiveRepository;

    public AuthService(JwtTokenProvider tokenProvider, AuthenticationManager authenticationManager, UserRepository repository, ExplosiveRepository explosiveRepository) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.explosiveRepository = explosiveRepository;
    }

    public ResponseEntity<TokenDTO> signIn(AccountCredentialsDTO data) {
        try {
            var email = data.getEmail();
            var password = data.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

            var user = repository.findByEmail(email).orElseThrow(
                    () -> new UsernameNotFoundException("Email " + email + " not found")
            );

            return ResponseEntity.ok(tokenProvider.createAccessToken(user.getEmail(), user.getRoles()));
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid email/password supplied");
        }
    }

    public ResponseEntity<TokenDTO> refreshToken(Long id, String refreshToken) {
        var user = repository.findById(id);

        if (user.isEmpty()) throw new UsernameNotFoundException("Id " + id + " not found");

        return ResponseEntity.ok(tokenProvider.refreshToken(refreshToken));
    }

    public ResponseEntity<TokenDTO> signUp(AccountCredentialsDTO data) {
        var user = repository.findByEmail(data.getEmail()).orElseThrow(
                () -> new UsernameNotFoundException("Email " + data.getEmail() + " not found")
        );

        var newUser = repository.save(new User(
                data.getEmail(),
                data.getPassword()
        ));

        return signIn(data);
    }
}
