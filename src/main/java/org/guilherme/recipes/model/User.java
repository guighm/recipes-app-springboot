package org.guilherme.recipes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "created_at")
    private Instant createdAt;

    @OneToMany(mappedBy = "user")
    private List<Recipe> recipes = new ArrayList<>();

}
