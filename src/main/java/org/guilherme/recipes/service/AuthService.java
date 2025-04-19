package org.guilherme.recipes.service;

import lombok.RequiredArgsConstructor;
import org.guilherme.recipes.config.user.UserDetailsConfig;
import org.guilherme.recipes.dto.JwtDto;
import org.guilherme.recipes.dto.LoginDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager manager;
    private final JwtService jwtService;

    public ResponseEntity<?> login(LoginDto dto) {
        try {
            Authentication authentication = manager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            dto.getEmail(),
                            dto.getPassword()
                    ));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtService.generateToken(authentication);
            UserDetailsConfig userDetailsConfig = (UserDetailsConfig) authentication.getPrincipal();
            return ResponseEntity.ok(new JwtDto(userDetailsConfig.getId(), jwt));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
