package org.guilherme.recipes.repository;

import org.guilherme.recipes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<User> findByName(String name);
}