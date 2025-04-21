package org.guilherme.recipes.config.seed;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.guilherme.recipes.model.User;
import org.guilherme.recipes.repository.UserRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.time.Instant;

@RequiredArgsConstructor
@Component
@Transactional
public class Seeder implements ApplicationListener<ApplicationReadyEvent> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
        System.out.println("App iniciado");
        seed();
        System.out.println("App povoado");
    }

    private void seed() {
        String email = "admin@email.com";
        User user = new User();
        if (!userRepository.existsByEmail(email)) {
            user.setName("admin");
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode("123"));
            user.setAvatarUrl("admin");
            user.setCreatedAt(Instant.now());
            userRepository.save(user);
        }
    }
}