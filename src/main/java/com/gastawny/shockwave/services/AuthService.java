package com.gastawny.shockwave.services;

import com.gastawny.shockwave.data.dto.AccountCredentialsDTO;
import com.gastawny.shockwave.data.dto.TokenDTO;
import com.gastawny.shockwave.entities.ExplosiveEntity;
import com.gastawny.shockwave.entities.UserEntity;
import com.gastawny.shockwave.repositories.ExplosiveRepository;
import com.gastawny.shockwave.repositories.UserRepository;
import com.gastawny.shockwave.security.jwt.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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
            var username = data.getUsername();
            var password = data.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var user = repository.findByUserName(username);

            var tokenResponse = new TokenDTO();

            if (user == null) throw new UsernameNotFoundException("Username " + username + " not found");

            tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());

            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    public ResponseEntity<TokenDTO> refreshToken(String username, String refreshToken) {
        var user = repository.findByUserName(username);

        var tokenResponse = new TokenDTO();

        if (user == null) throw new UsernameNotFoundException("Username " + username + " not found");

        tokenResponse = tokenProvider.refreshToken(refreshToken);

        return ResponseEntity.ok(tokenResponse);
    }

    public ResponseEntity<TokenDTO> signUp(AccountCredentialsDTO data) {
        var user = repository.findByUserName(data.getUsername());

        if (user != null) {
            return ResponseEntity.badRequest().body(null);
        }

        var newUser = repository.save(new UserEntity(
                data.getUsername(),
                data.getPassword()
        ));

        return signIn(data);
    }

    public List<ExplosiveEntity> getExplosives() {
        return explosiveRepository.findAll();
    }
}
