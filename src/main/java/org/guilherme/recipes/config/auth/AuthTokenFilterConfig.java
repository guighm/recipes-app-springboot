package org.guilherme.recipes.config.auth;

import lombok.RequiredArgsConstructor;
import org.guilherme.recipes.config.user.UserDetailsServiceConfig;
import org.guilherme.recipes.service.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AuthTokenFilterConfig {

    private final JwtService jwtService;

    private final UserDetailsServiceConfig userDetailsServiceConfig;

    @Bean
    public AuthTokenFilter authTokenFilter() {
        return new AuthTokenFilter(jwtService, userDetailsServiceConfig);
    }
}
