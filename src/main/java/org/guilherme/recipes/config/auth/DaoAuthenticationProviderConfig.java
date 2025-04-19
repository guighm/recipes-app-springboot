package org.guilherme.recipes.config.auth;

import lombok.RequiredArgsConstructor;
import org.guilherme.recipes.config.user.UserDetailsServiceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DaoAuthenticationProviderConfig {

    private final UserDetailsServiceConfig userDetailsServiceConfig;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsServiceConfig);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }
}
