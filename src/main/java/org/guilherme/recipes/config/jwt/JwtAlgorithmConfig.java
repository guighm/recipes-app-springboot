package org.guilherme.recipes.config.jwt;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.UnsupportedEncodingException;

@Configuration
public class JwtAlgorithmConfig {

    @Value("${auth.token.secret}")
    private String jwtSecret;

    @Bean
    public Algorithm algorithm() throws UnsupportedEncodingException {
        return Algorithm.HMAC256(jwtSecret);
    }
}
