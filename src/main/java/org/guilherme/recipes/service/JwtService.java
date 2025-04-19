package org.guilherme.recipes.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.RequiredArgsConstructor;
import org.guilherme.recipes.config.user.UserDetailsConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${auth.token.issuer}")
    private String issuer;

    @Value("${auth.token.expiration}")
    private int expiration;

    private final Algorithm algorithm;

    public String generateToken(Authentication authentication) {

        UserDetailsConfig userDetailsConfig = (UserDetailsConfig) authentication.getPrincipal();
        Date hoje = new Date();
        Date futuro = new Date(hoje.getTime() + expiration);

        return JWT.create()
                .withIssuer(issuer)
                .withSubject(userDetailsConfig.getEmail())
                .withClaim("id", userDetailsConfig.getId())
                .withIssuedAt(hoje)
                .withExpiresAt(futuro)
                .sign(algorithm);
    }

    public String getUsername(String token) {
        return JWT.require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(token)
                .getSubject();
    }

    public Long getId(String token) {
        return JWT.require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(token)
                .getClaim("id")
                .asLong();
    }

    public boolean validate(String token) {
        try {
            JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token);
            return true;
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException(e.getMessage());
        }
    }
}
